package service;


import dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import mapper.ProductMapper;
import model.Coordinates;
import model.Location;
import model.Person;
import model.Product;
import repository.ProductRepository;
import service.exceptions.PermissionDeniedModificationException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    @Transactional
    public void create(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        Coordinates coordinates = product.getCoordinates();
        Person person = product.getPerson();
        Location location = person.getLocation();
        location.setPerson(person);
        coordinates.setProduct(product);
        person.setProduct(product);

        productRepository.save(product);
    }

    @Transactional
    public boolean update(long userId, ProductDTO productDTO) throws PermissionDeniedModificationException {
        Optional<Product> maybeProduct = productRepository.findById(productDTO.getId());
        if (!maybeProduct.isPresent()) return false;

        Product product = maybeProduct.get();
        Product dtoEntity = ProductMapper.INSTANCE.toEntity(productDTO);
        if (product.getUser().getId() == userId) {
            if (dtoEntity.getName() != null) product.setName(dtoEntity.getName());
            if (dtoEntity.getCoordinates() != null) product.setCoordinates(dtoEntity.getCoordinates());
            if (dtoEntity.getPrice() > 0) product.setPrice(dtoEntity.getPrice());
            if (dtoEntity.getPartNumber() != null) product.setPartNumber(dtoEntity.getPartNumber());
            if (dtoEntity.getManufactureCost() != 0) product.setManufactureCost(dtoEntity.getManufactureCost());
            if (dtoEntity.getUnitOfMeasure() != null) product.setUnitOfMeasure(dtoEntity.getUnitOfMeasure());
            if (dtoEntity.getPerson() != null) product.setPerson(dtoEntity.getPerson());
            productRepository.update(product);
            return true;
        }
        throw new PermissionDeniedModificationException("You cannot change this object");
    }

    @Transactional
    public long clear(long userId) {
        return productRepository.clearByUserId(userId);
    }
    @Transactional
    public long removeGreater(long userid, double price) {
        return productRepository.removeGreater(userid, price);
    }
    @Transactional
    public long removeLower(long userid, double price) {
        return productRepository.removeLower(userid, price);
    }
    @Transactional
    public boolean removeById(long userId, long id) {
        return productRepository.removeById(userId, id);
    }
    @Transactional
    public long removePartNumber(long userId, String partNumber) {
        List<Product> allByUserId = productRepository.findAllByUserId(userId);
        AtomicLong count = new AtomicLong();
        allByUserId.forEach(product -> {
            if (product.getPartNumber().startsWith(partNumber)) {
                productRepository.delete(product.getId());
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    //SHOW
    @Transactional
    public List<ProductDTO> findAll() {
        List<Product> all = productRepository.findAll();
        return all.stream().map(ProductMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
    @Transactional
    public List<ProductDTO> filterGreater(double price) {
        List<Product> greater = productRepository.findGreater(price);
        return greater.stream().map(ProductMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
    @Transactional
    public List<ProductDTO> filterLower(double price) {
        List<Product> lower = productRepository.findLower(price);
        return lower.stream().map(ProductMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
    @Transactional
    public List<ProductDTO> filterPartNumber(String partNumber) {
        List<ProductDTO> allDTO = productRepository.findAll().stream().map(ProductMapper.INSTANCE::toDTO).collect(Collectors.toList());
        List<ProductDTO> filteredList = null;
        allDTO.forEach(productDTO -> {
            if (productDTO.getPartNumber().startsWith(partNumber)) {
                filteredList.add(productDTO);
            }
        });
        return filteredList;
    }
    @Transactional
    public String info(long userId) {
        long countByUser = productRepository.findAllByUserId(userId).size();
        long all = productRepository.findAll().size();
        StringBuilder sb = new StringBuilder().append(String.format("Count of all elements: %d", all))
                .append("\n")
                .append(String.format("The count of items created by user %d", countByUser));
        return sb.toString();
    }




    /*
     * Пользователь может менять/удалять только то, что создал.
     * Нужно всегда проверять сперва, что объект создал именно тот пользователь, который отправил запрос
     * Теперь внутри Request должна приходить не только команда, но и UserDTO!
     *   Add Update Clear RemoveGC RemoveID RemoveL RemovePN
     * команда Add:
     *   1)приходит ProductDTO.
     *   2)Маппим в Entity.
     *   3)Сетим нужные поля//просто приватный метод внутри сервиса, который будет сетить нужные поля. По идее
     *   это внутри маппинга dto->entity нужно делать, но мапстракт так не умеет вроде. Там можно кастомные сеттеры создавать,
     *   но не такого рода
     *   4)вызываем у productRepository метод save
     *
     * команда Update:
     *   1) Внутри команды Update вытащить из Request->UserDTO->id
     *   2) внутри метода update(int userid, ProductDTO):
     *       - запрос select from product where id = id
     *       - если ответ пуст возвращаем false -> потом передаем ответ Элемент с таким ID отсутствует
     *       - иначе
     *           проверка if (product.userId == userid) {
     *           поменять тупо поля с помощью set
     *              product.setField(productDTO.getField)
     *           иначе пробросить кастомный экспешн, что пользователь не может изменить объект, который не создал сам
     *       }
     *
     *   Команда Clear:
     *       - вытащить id из UserDTO передать в сервис
     *       - написать запрос внутри репозитория delete from product where user_id =: id
     *       - executeQuery вернет количество удаленных элементов.  Это количество вернуть обратно в команду Clear
     *       - сравнить с 0 и передать нужное сообщение о клиенту
     *
     *   Команда RemoveGC
     *       -написать запрос delete from product where user_id = id AND product.price>price который пришел из запроса
     *           по идее это не сложный запрос, просто с двумя условиям.
     *           фильтрацию можно было сделать на стороне жавы. Достать все элементы, которые создал user,
     *           потом filter по условию, потом delete, но так как условие у тебя простое сравнения price, то можно сразу
     *           запрос написать
     *       -опять же из сервиса вернуть количесто удаленных элементов, передать нормальное сообщение в ответ команды
     *   Аналогично RemoveL только условия <price
     *
     *   Команда RemoveID
     *       я бы сделал так
     *       select from product where id = id
     *           если ответ пустой, то сказать клиенту, что элемент с таким id Отсутствует
     *       иначе сравнить user.id из объекта полученного
     *       если совпало, то удалить)) вернуть из метода true
     *
     *   команда RemovePN
     *       delete from product where partNumber = строка, которая пришла AND user.id = id
     *           вернуть количество удаленных элементов
     *
     *   Команды, которые не изменяю сущности, а просто выводят информацию. Здесь мы выводим инфу о всех объектах. Не нужно сравнивать
     *   по user id
     *
     *   FilterL FilterPN Help History(неприятная), Info, Show
     *
     *       SHOW просто вызвать findAll
     *       Команда show получит list<ProductDTO>. Из нее сформировать строку и передать ответ
     *
     *       Info тип коллекции уже не нужно выводить. Можно вывести количство всех объектов и количество объектов, созданных пользователем
     *
     *       Help
     *
     *       команда FilterL select from product where user_id = id AND product.price<price
     *
     *       команда FilterG  select from product where user_id = id AND product.price>price
     *
     *       history
     *
     *
     *
     *
     * */

}
