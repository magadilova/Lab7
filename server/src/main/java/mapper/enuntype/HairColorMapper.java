package mapper.enuntype;


import dto.enumdto.HairColorDTO;
import model.enumtype.HairColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface HairColorMapper {
    HairColorMapper INSTANCE = Mappers.getMapper(HairColorMapper.class);
    HairColorDTO toDTO(HairColor hairColor);

    HairColor toEntity(HairColorDTO hairColorDTO);
}
