package by.ilyin.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ReachedCheckpointInfoDTO {

    private LocalDateTime achieveTime;
    private String checkpointAddress;

}
