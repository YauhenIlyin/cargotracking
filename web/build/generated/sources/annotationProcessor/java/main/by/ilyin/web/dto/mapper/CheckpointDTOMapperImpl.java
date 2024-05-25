package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetCheckpointResponseDTO;
import by.ilyin.web.entity.Checkpoint;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T03:51:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CheckpointDTOMapperImpl implements CheckpointDTOMapper {

    @Override
    public GetCheckpointResponseDTO mapToDTO(Checkpoint checkpoint) {
        if ( checkpoint == null ) {
            return null;
        }

        GetCheckpointResponseDTO getCheckpointResponseDTO = new GetCheckpointResponseDTO();

        getCheckpointResponseDTO.setAddress( checkpoint.getAddress() );
        getCheckpointResponseDTO.setRequiredArrivalDate( checkpoint.getRequiredArrivalDate() );
        getCheckpointResponseDTO.setCheckpointDate( checkpoint.getCheckpointDate() );

        return getCheckpointResponseDTO;
    }
}
