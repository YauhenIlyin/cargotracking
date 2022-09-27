package by.ilyin.core.entity.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
public class AppInfoDTO {

    private String applicationName;
    private String version;

}
