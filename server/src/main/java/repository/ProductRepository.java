package repository;


import model.Product;
import org.hibernate.Session;

import java.util.List;


public class ProductRepository extends RepositoryBase<Long, Product> {
    public ProductRepository(Session session) {
        super(Product.class, session);
    }

    public long clearByUserId(long userId){
        return getSession().createQuery("delete from Product where user.id=:userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public long removeGreater(long userId, double price){
        return getSession().createQuery("delete from Product where user.id=:userId and price>=:price")
                .setParameter("userId", userId)
                .setParameter("price", price)
                .executeUpdate();
    }

    public List<Product> findAllByUserId(long userId){
        return getSession().createQuery("select product from Product product where product.user.id=:userId", Product.class)
                .setParameter("userId", userId).list();
    }

    public long removeLower(long userId, double price){
        return getSession().createQuery("delete from Product where user.id=:userId and price<=:price")
                .setParameter("userId", userId)
                .setParameter("price", price)
                .executeUpdate();
    }

    public boolean removeById(long userId, long id){
        return getSession().createQuery("delete from Product where user.id=:userId and id=:id")
                .setParameter("userId", userId)
                .setParameter("id", id)
                .executeUpdate()==1;
    }

    public List<Product> findGreater(double price){
        return getSession().createQuery("select product from Product product where product.price>=:price", Product.class)
                .setParameter("price", price)
                .list();
    }

    public List<Product> findLower(double price){
        return getSession().createQuery("select product from Product product where product.price<=:price", Product.class)
                .setParameter("price", price)
                .list();
    }

//    public long getCountOfProducts(){
//        return getSession().createQuery("select count(*) from Product").executeUpdate();
//    }
}
