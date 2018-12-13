package viewer.android.gaiaproject.eu.gaiaviewer.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SchoolModel implements Serializable {
    String name;
    UUID uuid;
    String path;
}
