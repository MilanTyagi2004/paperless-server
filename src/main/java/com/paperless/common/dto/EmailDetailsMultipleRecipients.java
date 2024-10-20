package com.paperless.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDetailsMultipleRecipients {

    private String[] recipient;
    private String msgBody;
    private String subject;
    private MultipartFile attachment;
    private String sender;

}
