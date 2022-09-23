package com.datavalid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @author lyq
 * @date 2022/3/8 11:12
 */
@Valid
@Getter
@Setter
@ToString
public class ValidDemo1 {
    @NotBlank(message = "不能为空，检查时会将空格忽略")
    @NotEmpty(message = "不能为空，这里的空是指空字符串")
    @Length(min = 10,max = 30,message = "长度为10-30")
    String name;
    @NotNull(message = "不能为Null")
    Integer age;

    @Pattern(regexp = "填写正则表达式")
    String phone;

    @Max(value = 20,message = "最大值为20")
    @Min(value = 10,message = "最少为10")
    int num;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "需要填写一个未来的日期")
    Date futureDate;

    @DecimalMax(value = "2000",message = "最大值为2000")
    @DecimalMin(value = "1000",message = "最少为1000")
    BigDecimal bigDecimal;

    @AssertTrue
    boolean isbl =true;
    @AssertFalse
    boolean isFalse =false;
    @Email
    String email;
    @URL(protocol = "http",host = "192.168.0.109",port = 6379)
    String url;

    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ValidDemo1 test = new ValidDemo1();
        test.setAge(50);
        test.setName("liyaqiu");
        System.out.println(test);
        Set<ConstraintViolation<ValidDemo1>> constraintViolationSet = validator.validate(test);
        for (ConstraintViolation<ValidDemo1> constraintViolation : constraintViolationSet) {
            System.out.println(constraintViolation.getMessage());
        }


    }
}
