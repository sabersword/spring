package springwebblank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springwebblank.domain.Product;
import springwebblank.domain.ProductExample;
import springwebblank.mapper.ProductMapper;

@Controller
public class KendoUIController {
	
    @Autowired
    private ProductMapper productMapper;
	
	@RequestMapping(value="products")
	@ResponseBody
	public Object query(String callback) throws Exception {
	    List<Product> products = productMapper.selectByExample(new ProductExample());
		if (callback == null)
			return products;
        
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(products);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	@RequestMapping(value="products/update")
	@ResponseBody
	public Object update(String callback, @RequestParam("models") List<Product> products) throws Exception {
	    for (Product product : products) {
	        productMapper.updateByPrimaryKey(product);
	    }
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(products);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	@RequestMapping(value="products/kendoui")
	public String test() throws Exception {
		return "kendoUIGrid";
	}
	
	@RequestMapping(value="products/destroy")
	@ResponseBody
	public Object destroy(String callback, @RequestParam("models") List<Product> products) throws Exception {
	    for (Product product : products) {
	        productMapper.deleteByPrimaryKey(product.getProductId());
        }
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(products);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	@RequestMapping(value="products/create")
	@ResponseBody
	public Object create(String callback, @RequestParam("models") List<Product> products) throws Exception {
	    for (Product product : products) {
	        productMapper.insert(product);
	    }
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(products);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
