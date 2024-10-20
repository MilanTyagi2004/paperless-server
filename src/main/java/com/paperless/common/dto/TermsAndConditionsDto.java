package com.paperless.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TermsAndConditionsDto {
    private String status;
    private S3ResponseMetadataDto uploadFileMetadata;
}
