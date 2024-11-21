package org.youcode.CITRONIX.shared.utils.validators.interfaces;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.youcode.CITRONIX.shared.utils.validators.EnumValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface IsValidEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid value for Enum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}