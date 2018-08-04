package springwebblank;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class MvcContextHolder implements ApplicationContextAware, ServletContextAware {

    private static ApplicationContext mvcApplicationContext;
    private static ServletContext servletContext;

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
}
