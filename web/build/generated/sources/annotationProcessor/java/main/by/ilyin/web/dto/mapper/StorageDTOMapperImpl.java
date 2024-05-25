package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetStorageByIdResponseDTO;
import by.ilyin.web.entity.Storage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class StorageDTOMapperImpl implements StorageDTOMapper {

    @Override
    public GetStorageByIdResponseDTO mapToDto(Storage storage) {
        if ( storage == null ) {
            return null;
        }

        GetStorageByIdResponseDTO getStorageByIdResponseDTO = new GetStorageByIdResponseDTO();

        getStorageByIdResponseDTO.setId( storage.getId() );
        getStorageByIdResponseDTO.setName( storage.getName() );
        getStorageByIdResponseDTO.setAddress( storage.getAddress() );

        return getStorageByIdResponseDTO;
    }
}
