//package com.paperless.common.mapper;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class CompanyMapper {
//
//    public static Page<CompanyGetAllDto> getCompanyDto(Page<Company> companyPage) {
//        List<CompanyGetAllDto> dtoList = companyPage.getContent().stream()
//                .map(CompanyMapper::toDto)
//                .collect(Collectors.toList());
//
//        return new PageImpl<>(dtoList, companyPage.getPageable(), companyPage.getTotalElements());
//    }
//
//    public static CompanyGetAllDto toDto(Company company) {
//        if (company == null) {
//            return null;
//        }
//
//        CompanyGetAllDto dto = new CompanyGetAllDto();
//        dto.setId(company.getId());
//        dto.setFirstName(company.getFirstName());
//        dto.setLastName(company.getLastName());
//        dto.setEmailId(company.getEmailId());
//        dto.setContactNumber(company.getContactNumber());
//        dto.setCompanyName(company.getCompanyName());
//        dto.setSocialMedia(company.getSocialMedia());
//        dto.setRole(company.getRole());
//        dto.setProfessional(company.getProfessional());
//        dto.setCity(company.getCity());
//        dto.setState(company.getState());
//        dto.setSpecialization(company.getSpecialization());
//        dto.setLanguage(company.getLanguage());
//        dto.setSubscription(toSubscriptionDto(company.getSubscription()));
//        dto.setCardHolderName(company.getCardHolderName());
//        dto.setCardNumber(company.getCardNumber());
//        dto.setExpiryDate(company.getExpiryDate());
//        dto.setCvv(company.getCvv());
//        dto.setUserId(company.getUserId());
//        dto.setActive(company.getActive());
//        dto.setTaxId(company.getTaxId());
//
//        return dto;
//    }
//
//    private static SubscriptionDto toSubscriptionDto(Subscription subscription) {
//        if (subscription == null) {
//            return null;
//        }
//        SubscriptionDto subscriptionDto = new SubscriptionDto();
//        subscriptionDto.setId(subscription.getId());
//        subscriptionDto.setName(subscription.getName());
//        subscriptionDto.setPackageTable(toPackageDto(subscription.getPackageTable()));
//        subscriptionDto.setPrice(subscription.getPrice());
//        subscriptionDto.setShowInWebsite(subscription.isShowInWebsite());
//        return subscriptionDto;
//
//    }
//
//    private static PackageTableDto toPackageDto(PackageTable packageTable) {
//        if (packageTable == null) {
//            return null;
//        }
//        PackageTableDto packageTableDto =new PackageTableDto();
//        packageTableDto.setId(packageTable.getId());
//        packageTableDto.setPackageName(packageTable.getPackageName());
//        packageTableDto.setDescription(packageTable.getDescription());
//        packageTableDto.setDuration(packageTable.getDuration());
//        packageTableDto.setAllowDrawing(packageTable.getAllowDrawing());
//        packageTableDto.setAllowedUser(packageTable.getAllowedUser());
//        packageTableDto.setFreeTrial(packageTable.getFreeTrial());
//        packageTableDto.setActive(packageTable.getActive());
//        return packageTableDto;
//    }
//}
