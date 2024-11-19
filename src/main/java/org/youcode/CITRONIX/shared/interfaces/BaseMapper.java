package org.youcode.CITRONIX.shared.interfaces;

public interface BaseMapper <T , DTO>{
    T toEntity(DTO dto);
    DTO entityToDto(T entity);
}
