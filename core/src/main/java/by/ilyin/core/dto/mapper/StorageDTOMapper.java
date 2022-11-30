package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.StorageDTO;
import by.ilyin.core.entity.Storage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorageDTOMapper {

    Storage mapFromDTO(StorageDTO storageDTO);

}
