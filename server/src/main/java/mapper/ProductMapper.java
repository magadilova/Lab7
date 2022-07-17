package mapper;


import dto.ProductDTO;
import mapper.enuntype.UnitOfMeasureMapper;
import model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = {CoordinatesMapper.class, UnitOfMeasureMapper.class,PersonMapper.class, UserMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}
