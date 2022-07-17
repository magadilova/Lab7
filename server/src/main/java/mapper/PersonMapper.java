package mapper;


import dto.PersonDTO;
import mapper.enuntype.CountryMapper;
import mapper.enuntype.EyeColorMapper;
import mapper.enuntype.HairColorMapper;
import model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {EyeColorMapper.class, HairColorMapper.class, CountryMapper.class,LocationMapper.class})
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(Person person);

    Person toEntity(PersonDTO personDTO);
}
