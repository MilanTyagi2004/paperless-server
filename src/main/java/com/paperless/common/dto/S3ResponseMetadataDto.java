package com.paperless.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class S3ResponseMetadataDto {

    private Long fileId;
    private String filePath;
    private String fileUrl;
    private String fileName;
    private String fileOriginalName;
    private long fileSize;
    private String fileContentType;
    private Date lastModified;
    private Date createdDate;
    private Long userId;
    private String uploadStatus;
    private Long companyId;

}
