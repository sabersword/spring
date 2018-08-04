package springwebblank;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RootContextHolder implements ApplicationContextAware {

    private static ApplicationContext rootApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RootContextHolder.rootApplicationContext = applicationContext;
    }

    public static ApplicationContext getRootApplicationContext() {
        return rootApplicationContext;
    }
}
