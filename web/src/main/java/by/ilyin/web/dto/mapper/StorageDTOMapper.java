package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetStorageByIdResponseDTO;
import by.ilyin.web.entity.Storage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageDTOMapper {

    GetStorageByIdResponseDTO mapToDto(Storage storage);

}
