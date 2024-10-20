package com.paperless.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private String sender;

}
