package utils;


import encryption.PasswordEncryptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.ProductRepository;
import repository.UserRepository;
import service.ProductService;
import service.UserService;
import utils.exceptions.ReadingFilePropertiesException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;


public class ServiceManager {
    private UserService userService;
    private ProductService personService;

    public ServiceManager() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ReadingFilePropertiesException {
        initAllServices();
    }

    private void initAllServices() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ReadingFilePropertiesException {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class}, (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(sessionFactory);
        UserRepository userRepository = new UserRepository(session);
        userService = new ByteBuddy()
                .subclass(UserService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(UserService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(PasswordEncryptor.class, UserRepository.class)
                .newInstance( new PasswordEncryptor("encryption.properties"), userRepository);

        ProductRepository personRepository = new ProductRepository(session);
        personService = new ByteBuddy()
                .subclass(ProductService.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(transactionInterceptor))
                .make()
                .load(ProductService.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(ProductRepository.class)
                .newInstance(personRepository);
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getPersonService() {
        return personService;
    }
}
