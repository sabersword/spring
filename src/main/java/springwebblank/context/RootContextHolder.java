package springwebblank.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RootContextHolder implements ApplicationContextAware, BeanFactoryAware {

    private static ApplicationContext rootApplicationContext;
    private static BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RootContextHolder.rootApplicationContext = applicationContext;
    }

    public static ApplicationContext getRootApplicationContext() {
        return rootApplicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        RootContextHolder.beanFactory = beanFactory;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
