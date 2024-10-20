package com.paperless.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class PackageRequestDto {
    private Long id;
    private String packageName;
    private Long allowDrawing;
    private String duration;
    private Boolean freeTrial;
    private String description;
//    private List<Subscription> subscription;
    private Boolean active;

}
