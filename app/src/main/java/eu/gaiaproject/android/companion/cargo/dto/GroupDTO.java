package eu.gaiaproject.android.companion.cargo.dto;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.sparkworks.cargo.common.dto.AuditDTO;

import java.util.UUID;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDTO extends AuditDTO {
    private UUID uuid;
    private String name;
    private String path;
    private boolean deleted;
}
