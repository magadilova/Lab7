package service;


import dto.UserDTO;
import encryption.PasswordEncryptor;
import encryption.exceptions.PasswordEncryptionException;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import model.User;
import repository.UserRepository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;


@RequiredArgsConstructor
public class UserService {
    private final PasswordEncryptor passwordEncryptor;
    private final UserRepository userRepository;
    @Transactional
    public boolean create(UserDTO userDTO) throws PasswordEncryptionException {
        if (findByLogin(userDTO.getLogin()).isPresent()) return false;
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        String salt = passwordEncryptor.getSalt();
        String hashedPassword = passwordEncryptor.getHash(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }
    @Transactional
    public Optional<UserDTO> findByLogin(String login) {
        try {
            return Stream.of(userRepository.findByLogin(login)).map(UserMapper.INSTANCE::toDTO).findFirst();
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    @Transactional
    public boolean exists(UserDTO userDTO) throws PasswordEncryptionException {
        try {
            User realUser = userRepository.findByLogin(userDTO.getLogin());
            if (realUser == null){
                return false;
            }
            String salt = realUser.getSalt();
            String maybeRealPassword = passwordEncryptor.getHash(userDTO.getPassword(), salt);
            return realUser.getPassword().equals(maybeRealPassword);
        } catch (NoResultException e) {
            return false;
        }
    }
}
