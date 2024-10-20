package com.paperless.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String contactNumber;
    private String companyName;
    private String socialMedia;
    private Long roleId;
    private Long professionalId;
    private Long cityId;
    private Long stateId;
    private Long specializationId;
    private Long languageId;
    private Long subscriptionId;
    private String cardHolderName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private Long userId; //jwtToken


}
