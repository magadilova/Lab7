package mapper;


import dto.LocationDTO;
import model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDTO toDTO(Location location);

    Location toEntity(LocationDTO locationDTO);
}
