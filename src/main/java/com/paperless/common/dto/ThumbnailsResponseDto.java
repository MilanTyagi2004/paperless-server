package com.paperless.common.dto;

import java.util.List;

public class ThumbnailsResponseDto {
    private int pdfId;
    private List<String> thumbnails;

    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
