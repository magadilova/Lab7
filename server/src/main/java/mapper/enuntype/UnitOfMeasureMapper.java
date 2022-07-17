package mapper.enuntype;


import dto.enumdto.UnitOfMeasureDTO;
import model.enumtype.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitOfMeasureMapper {
    UnitOfMeasureMapper INSTANCE = Mappers.getMapper(UnitOfMeasureMapper.class);
    UnitOfMeasureDTO toDTO(UnitOfMeasure unitOfMeasure);

    UnitOfMeasure toEntity(UnitOfMeasureDTO unitOfMeasureDTO);
}
