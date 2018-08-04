package springwebblank;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		System.out.println("ypq");
		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
		xmlWebApplicationContext.setConfigLocation("classpath:spring-mvc-servletTest.xml");
		ServletRegistration.Dynamic dispatcher = context.addServlet("spring-mvc", new DispatcherServlet(xmlWebApplicationContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		
		context.addListener(new LogbackConfigListener());
		context.setInitParameter("logbackConfigLocation", "classpath:logbackTest.xml");
		
		context.addListener(new ContextLoaderListener());
		context.setInitParameter("contextConfigLocation", "/WEB-INF/root-context.xml");
	}

}
