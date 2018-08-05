package springwebblank;

import freemarker.ext.dom.NodeModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import springwebblank.context.MvcContextHolder;
import springwebblank.context.RootContextHolder;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors;

@Controller
public class MyController {
	
	@Autowired(required=false)
	private WebApplicationInitializer wai;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	private ApplicationContext applicationContext;
	
//	@Autowired  
//    private Configuration freeMarkerConfiguration;

	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;
	private static SSLContextBuilder builder = null;

	static {
		try {
			builder = new SSLContextBuilder();
			// 全部信任 不做身份鉴定
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register(HTTP, new PlainConnectionSocketFactory())
					.register(HTTPS, sslsf)
					.build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(200);//max connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("hello")
	public String hello(Model model, ServletRequest request) throws Exception {
		ApplicationContext mac = MvcContextHolder.getMvcApplicationContext();
		ApplicationContext rac = RootContextHolder.getRootApplicationContext();
		ServletContext sc = MvcContextHolder.getServletContext();

		// MvcApplicationContext对于两个bean都能获取到(因为他的parent为RootApplicationContext)
		FormattingConversionService customConversionServiceByMvc = (FormattingConversionService) MvcContextHolder.getBeanFactory().getBean("CustomConversionService");
		FreeMarkerConfigurer freeMarkerConfigurerByMvc = (FreeMarkerConfigurer) MvcContextHolder.getBeanFactory().getBean("freeMarkerConfigurer");

		// RootApplicationContext只能获取到自己的bean, 获取不到MvcApplicationContext, 因为他的parent是null
		FormattingConversionService customConversionServiceByRoot = (FormattingConversionService) RootContextHolder.getBeanFactory().getBean("CustomConversionService");
		FreeMarkerConfigurer freeMarkerConfigurerByRoot = (FreeMarkerConfigurer) RootContextHolder.getBeanFactory().getBean("freeMarkerConfigurer");


		Logger logger = LoggerFactory.getLogger(MyController.class);
		logger.debug("this is debug");
		logger.info("this is info");
		logger.warn("this is warn");
		logger.error("this is error");
		System.out.println("1111");
		System.out.println("wai=" + wai);
		model.addAttribute("greeting", "hello spring mvc");
		
		//test freemarker
//		Configuration configuration = new Configuration();
//		configuration.setDirectoryForTemplateLoading(new File("MSG11.ftl"));
		Map<String, NodeModel> map = new HashMap<String, NodeModel>();
//		map.put("name", "world");
//		map.put("age", "11");
//		map.put("addr", "china");
//		map.put("root", NodeModel.parse(new File("E:/freemarkerTemplate/source.xml")));
//		NodeModel nodeModel = map.get("root");
//		System.out.println(map.get("root"));
		
//		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("MSG11.ftl");
//		template.process(map, new FileWriter(new File("E:/freemarkerTemplate/target")));
		
		return "helloworld";
	}

	public static CloseableHttpClient getHttpClient() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
//		CloseableHttpClient httpClient = HttpClients.custom()
//				.setSSLSocketFactory(sslsf)
//				.setConnectionManager(cm)
//				.setConnectionManagerShared(true)
//				.build();
		return httpClient;
	}

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = getHttpClient();
		String retrieveUrl = "https://217.115.205.17/asp/automatic.asp?"
				+ "action=retrieve"
				+ "&factor=CN00900"
				+ "&user=ceshi2"
				+ "&password=111111111";

		try {
			HttpUriRequest request = new HttpGet(retrieveUrl);
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		} catch (Exception e) {
		} finally {
			httpClient.close();
		}

	}
}
