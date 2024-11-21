package org.youcode.CITRONIX.shared.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.youcode.CITRONIX.shared.utils.validators.interfaces.IsValidEnum;

public class EnumValidator implements ConstraintValidator<IsValidEnum, Enum<?>> {
    private Enum<?>[] enumConstants;

    @Override
    public void initialize(IsValidEnum constraintAnnotation) {
        this.enumConstants = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        for (Enum<?> constant : enumConstants) {
            if (constant.name().equals(value.name())) {
                return true;
            }
        }
        return false;
    }
}