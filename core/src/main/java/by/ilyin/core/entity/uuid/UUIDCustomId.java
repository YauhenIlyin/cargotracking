package by.ilyin.core.entity.uuid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UUIDCustomId implements Serializable {

    private Long userId;
    private CustomUUID.Destination destination;

}
