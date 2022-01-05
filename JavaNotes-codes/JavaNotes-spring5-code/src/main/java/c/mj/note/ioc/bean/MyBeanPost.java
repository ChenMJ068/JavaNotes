package c.mj.note.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * create class MyBeanPost.java @version 1.0.0 by @author ChenMJ @date 2021-12-24 16:50:00
 */
@Component
public class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("----初始化之前执行 postProcessBeforeInitialization---");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----初始化之后执行 postProcessAfterInitialization------");
        return bean;
    }
}
