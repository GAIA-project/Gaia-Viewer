package viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WsDTO {
    private long timestamp;
    private String resourceUri;
    private double value;
}
