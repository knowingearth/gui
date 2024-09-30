package com.y.gui.common.annotations.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InValidator implements ConstraintValidator<In, String> {

    private In constraintAnnotation;

    @Override
    public void initialize(In constraintAnnotation) {
        // nothing to do
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.hasLength(value)){
            //接受空值
            return true;
        }
        for(String model : constraintAnnotation.value()){
            if(value.equals(model)){
                return true;
            }
        }
        return false;
    }

}
