package by.ilyin.core.dto.mapper;

import by.ilyin.core.dto.CheckpointDTO;
import by.ilyin.core.dto.WaybillDTO;
import by.ilyin.core.entity.Checkpoint;
import by.ilyin.core.entity.Waybill;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-25T01:42:43+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class WaybillDTOMapperImpl implements WaybillDTOMapper {

    @Override
    public Waybill mapFromDTO(WaybillDTO waybillDTO) {
        if ( waybillDTO == null ) {
            return null;
        }

        Waybill waybill = new Waybill();

        waybill.setDistance( waybillDTO.getDistance() );
        waybill.setEndDate( waybillDTO.getEndDate() );
        waybill.setCheckpoints( checkpointDTOListToCheckpointList( waybillDTO.getCheckpoints() ) );

        return waybill;
    }

    protected Checkpoint checkpointDTOToCheckpoint(CheckpointDTO checkpointDTO) {
        if ( checkpointDTO == null ) {
            return null;
        }

        Checkpoint checkpoint = new Checkpoint();

        checkpoint.setAddress( checkpointDTO.getAddress() );
        checkpoint.setRequiredArrivalDate( checkpointDTO.getRequiredArrivalDate() );

        return checkpoint;
    }

    protected List<Checkpoint> checkpointDTOListToCheckpointList(List<CheckpointDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Checkpoint> list1 = new ArrayList<Checkpoint>( list.size() );
        for ( CheckpointDTO checkpointDTO : list ) {
            list1.add( checkpointDTOToCheckpoint( checkpointDTO ) );
        }

        return list1;
    }
}
