package by.ilyin.web.dto.mapper;

import by.ilyin.web.dto.response.GetCheckpointResponseDTO;
import by.ilyin.web.entity.Checkpoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckpointDTOMapper {

    GetCheckpointResponseDTO mapToDTO(Checkpoint checkpoint);

}
