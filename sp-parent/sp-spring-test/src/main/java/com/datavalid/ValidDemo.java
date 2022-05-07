package com.datavalid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author lyq
 * @date 2022/3/8 11:12
 */
@Valid
@Getter
@Setter
@ToString
public class ValidDemo {
    @NotNull(message = "空空")
    String name;
    @Max(value = 20,message = "最大值为20")
    @Min(value = 10,message = "最少为10")
    int age;


    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ValidDemo test = new ValidDemo();
        test.setAge(50);
        test.setName("liyaqiu");
        System.out.println(test);
        Set<ConstraintViolation<ValidDemo>> constraintViolationSet = validator.validate(test);
        for (ConstraintViolation<ValidDemo> constraintViolation : constraintViolationSet) {
            System.out.println(constraintViolation.getMessage());
        }


    }
}
