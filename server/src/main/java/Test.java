//import dto.ProductDTO;
//import dto.UserDTO;
//import mapper.ProductMapper;
//import model.*;
//import model.enumtype.Country;
//import model.enumtype.EyeColor;
//import model.enumtype.HairColor;
//import model.enumtype.UnitOfMeasure;
//import service.ProductService;
//import service.UserService;
//import utils.ServiceManager;
//import utils.exceptions.ReadingFilePropertiesException;
//
//import java.lang.reflect.InvocationTargetException;
//import java.time.LocalDate;
//
//public class Test {
//    public static void main(String[] args) throws NoSuchMethodException, ReadingFilePropertiesException, InstantiationException, IllegalAccessException, InvocationTargetException {
//        User user = User.builder()
//                .id(1)
//                .login("gggg")
//                .password("gggg")
//                .salt("fasd")
//                .build();
//        Location location = Location.builder()
//                .x(5.0F)
//                .y(5.0D)
//                .z(6.4D)
//                .build();
//        Person person = Person.builder()
//                .name("Malika")
//                .passportID("fsdaf")
//                .eyeColor(EyeColor.BLUE)
//                .hairColor(HairColor.BLACK)
//                .nationality(Country.FRANCE)
//                .location(location)
//                .build();
//        location.setPerson(person);
//        Coordinates coordinates = Coordinates.builder()
//                .x(5).y(5L).build();
//        Product product = Product.builder()
//                .name("Yogurt")
//                .partNumber("fsdaf")
//                .coordinates(coordinates)
//                .user(user)
//                .creationDate(LocalDate.now())
//                .manufactureCost(4)
//                .person(person)
//                .price(234.5D)
//                .unitOfMeasure(UnitOfMeasure.CENTIMETERS)
//                .build();
//        coordinates.setProduct(product);
//        person.setProduct(product);
//        ServiceManager serviceManager = new ServiceManager();
//        ProductDTO productDTO = ProductMapper.INSTANCE.toDTO(product);
//
//        ProductService productService = serviceManager.getPersonService();
//        UserService userService = serviceManager.getUserService();
//        UserDTO realUser = userService.findByLogin(user.getLogin()).get();
//        productDTO.setUser(realUser);
//        productService.create(productDTO);
//
//
//    }
//}
