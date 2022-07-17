package mapper.enuntype;


import dto.enumdto.EyeColorDTO;
import model.enumtype.EyeColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface EyeColorMapper {
    EyeColorMapper INSTANCE = Mappers.getMapper(EyeColorMapper.class);

    EyeColorDTO toDTO(EyeColor eyeColor);

    EyeColor toEntity(EyeColorDTO eyeColorDTO);

}
