package com.paperless.common.mapper;

import com.paperless.common.dto.S3ResponseMetadataDto;
import com.paperless.common.dto.UploadedFileDto;

public class PdfFileMapper {

    /**
     * Converts an S3ResponseMetadataDto to an UploadedFileDto.
     *
     * @param s3ResponseMetadataDto The source DTO with S3 response metadata.
     * @param pdfId The PDF ID to be set in the target DTO.
     * @return An UploadedFileDto populated with data from the source DTO and additional PDF ID.
     */
    public static UploadedFileDto toUploadedFileDto(S3ResponseMetadataDto s3ResponseMetadataDto,UploadedFileDto uploadedFileDto , String pdfId) {
        if (s3ResponseMetadataDto == null) {
            return null;
        }

        // Create and populate the UploadedFileDto
        uploadedFileDto.setFileId(s3ResponseMetadataDto.getFileId());
        uploadedFileDto.setFilePath(s3ResponseMetadataDto.getFilePath());
        uploadedFileDto.setFileUrl(s3ResponseMetadataDto.getFileUrl());
        uploadedFileDto.setFileName(s3ResponseMetadataDto.getFileName());
        uploadedFileDto.setFileOriginalName(s3ResponseMetadataDto.getFileOriginalName());
        uploadedFileDto.setFileSize(s3ResponseMetadataDto.getFileSize());
        uploadedFileDto.setFileContentType(s3ResponseMetadataDto.getFileContentType());
        uploadedFileDto.setLastModified(s3ResponseMetadataDto.getLastModified());
        uploadedFileDto.setCreatedDate(s3ResponseMetadataDto.getCreatedDate());
        uploadedFileDto.setUserId(s3ResponseMetadataDto.getUserId());
        uploadedFileDto.setUploadStatus(s3ResponseMetadataDto.getUploadStatus());
        uploadedFileDto.setCompanyId(s3ResponseMetadataDto.getCompanyId());
        uploadedFileDto.setPdf_id(pdfId);
        return uploadedFileDto;
    }
}
