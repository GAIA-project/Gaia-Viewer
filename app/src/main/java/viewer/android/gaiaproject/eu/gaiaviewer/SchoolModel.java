package viewer.android.gaiaproject.eu.gaiaviewer;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto.GroupDTO;

@Getter
@Setter
@AllArgsConstructor
class SchoolModel implements Serializable {
    String name;
    UUID uuid;
    String path;
}
