package com.paperless.common.dto;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileUploadResponseResultDto {
    private String resourceUrl;
    private PutObjectResult putObjectResult;
}
