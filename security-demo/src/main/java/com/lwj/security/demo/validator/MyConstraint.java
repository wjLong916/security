package com.lwj.security.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个是自定义验证的Annotation；处理类是 ：MyConstraintValidator；按Hibernate validate的约定。这三个属性是必须的。
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MyConstraintValidator.class)
public @interface MyConstraint {
    String message() default "{org.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
