package mapper;


import dto.CoordinatesDTO;
import model.Coordinates;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CoordinatesMapper {
    CoordinatesMapper INSTANCE = Mappers.getMapper(CoordinatesMapper.class);

    CoordinatesDTO toDTO(Coordinates coordinates);

    Coordinates toEntity(CoordinatesDTO coordinatesDTO);

}
