package com.paperless.paperless_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.paperless.paperless_server.repository.DocumentRepository;
import com.paperless.paperless_server.service.DocumentService;

    @SpringBootTest
public class DocumentServiceTest {
    @Autowired
    private DocumentService documentService;

    @MockBean
    private DocumentRepository documentRepository;

    @Test
    public void testUploadDocument() {
        MultipartFile mockFile = new MockMultipartFile("file", "test.pdf", "application/pdf", "Test Content".getBytes());
        String response = documentService.uploadDocument(mockFile);
        assertEquals("File uploaded successfully!", response);
    }

    @Test
    public void testGetDocumentById() {
        Document mockDocument = new Document();
        mockDocument.setId(1L);
        mockDocument.setName("test.pdf");
        Mockito.when(documentRepository.findById(1L)).thenReturn(Optional.of(mockDocument));

        Document document = documentService.getDocumentById(1L);
        assertEquals("test.pdf", document.getName());
    }
}


