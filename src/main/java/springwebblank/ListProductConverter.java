package springwebblank;

import java.io.IOException;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springwebblank.domain.Product;

public class ListProductConverter implements Converter<String, List<Product>> {

	@Override
	public List<Product> convert(String source) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Product.class); 
		List<Product> productList;
		try {
			productList = (List<Product>)mapper.readValue(source, javaType);
			return productList;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
