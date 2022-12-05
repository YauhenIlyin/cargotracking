package by.ilyin.core.repository;

import by.ilyin.core.entity.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Long>, JpaSpecificationExecutor<Checkpoint> {

    Checkpoint findFirstByWaybillIdAndCheckpointDate(Long waybillId, LocalDate checkpointDate);

}
