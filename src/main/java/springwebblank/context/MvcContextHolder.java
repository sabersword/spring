package springwebblank.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class MvcContextHolder implements ApplicationContextAware, ServletContextAware, BeanFactoryAware {

    private static ApplicationContext mvcApplicationContext;
    private static ServletContext servletContext;
    private static BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MvcContextHolder.mvcApplicationContext = applicationContext;
    }

    public static ApplicationContext getMvcApplicationContext() {
        return mvcApplicationContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        MvcContextHolder.servletContext = servletContext;
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        MvcContextHolder.beanFactory = beanFactory;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
