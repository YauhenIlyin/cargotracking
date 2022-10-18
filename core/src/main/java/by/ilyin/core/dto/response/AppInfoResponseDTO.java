package by.ilyin.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppInfoResponseDTO {

    private String applicationName;
    private String version;

}