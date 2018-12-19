package eu.gaiaproject.android.companion.cargo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.sparkworks.cargo.common.dto.AuditDTO;
import net.sparkworks.cargo.common.enumerator.ResourceType;

import java.util.UUID;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDTO extends AuditDTO {
    private UUID uuid;
    private String userFriendlyName;
    private String systemName;
    private ResourceType type;
    private String controls;
    private UUID phenomenonUuid;
    private UUID unitUuid;
    private boolean deleted;
    private UUID groupUuid;
}
