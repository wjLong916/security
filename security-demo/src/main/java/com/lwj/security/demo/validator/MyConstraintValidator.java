package com.lwj.security.demo.validator;

import com.lwj.security.demo.service.IHellorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 这个是MyConstraint这个Annotation的处理类
 * 这个类可以按Spring 的组件一样是用。无需另外指定配置，如配置@Service，是不需要指定的。
 * @author 钱隆
 * @create 2017-10-01 20:37
 **/
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

    @Autowired
    private IHellorService hellorService;

    @Override
    public void initialize(MyConstraint myConstraint) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(hellorService.getHellor());
        System.out.println("这是字段值: "+o);
        return false;
    }
}
