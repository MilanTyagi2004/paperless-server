package com.paperless.paperless_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.paperless.paperless_server.model.Document;
import com.paperless.paperless_server.repository.DocumentRepository;
import com.paperless.paperless_server.exception.ResourceNotFoundException;



@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public String uploadDocument(MultipartFile file) {
        // Logic to save the file in the system or database
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setSize(file.getSize());
        documentRepository.save(document);
        return "File uploaded successfully!";
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Document not found with ID: " + id));
    }

    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Document not found with ID: " + id);
        }
        documentRepository.deleteById(id);
    }
}

