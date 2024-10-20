package com.paperless.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadedFileDto extends S3ResponseMetadataDto {
    private String pdf_id;
    private Boolean uploadedToPLBucket;
    private Boolean uploadedToAIBucket;
    private String errorInUploadingToAIBucket;

}
