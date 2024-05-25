package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.StorageDTO;
import by.ilyin.core.entity.Storage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:42+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class StorageDTOMapperImpl implements StorageDTOMapper {

    @Override
    public Storage mapFromDTO(StorageDTO storageDTO) {
        if ( storageDTO == null ) {
            return null;
        }

        Storage storage = new Storage();

        storage.setName( storageDTO.getName() );
        storage.setAddress( storageDTO.getAddress() );

        return storage;
    }
}
