package javax.frame.tools.task_interact.modules.commons.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class SpringUtil {

    @Resource
    ApplicationContext applicationContext;

    private static ApplicationContext context;

    @PostConstruct
    public void init() {
        SpringUtil.context = this.applicationContext;
    }

    public static <T> T getBean(Class clazz) {
        return (T) context.getBean(clazz);
    }
}
