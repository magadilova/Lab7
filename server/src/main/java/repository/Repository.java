package repository;




import model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface Repository<Key extends Serializable,Entity extends BaseEntity<Key>> {

    Entity save(Entity entity);

    void delete (Key id);

    void update (Entity entity);

    Optional<Entity> findById(Key id);

    List<Entity> findAll();

}
