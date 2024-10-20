package com.paperless.common.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PLMapper {

    PLMapper INSTANCE = Mappers.getMapper(PLMapper.class);

//    // Mapping from DTO to Entity
//    <D, E> E mapToEntity(D dto, Class<E> entityClass);
//
//    // Mapping from Entity to DTO
//    <E, D> D mapToDto(E entity, Class<D> dtoClass);
//
//    // Mapping from DTO to DTO
//    <D1, D2> D2 mapDtoToDto(D1 sourceDto, Class<D2> targetDtoClass);


//    Support toSupportEntity(SupportRequestDto supportRequestDto);

}
