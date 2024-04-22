package com.dhfinalproject.productservice;

import com.dhfinalproject.productservice.dto.CategoryDTO;
import com.dhfinalproject.productservice.dto.ProductDTO;
import com.dhfinalproject.productservice.repository.ICategoryRepository;
import com.dhfinalproject.productservice.repository.IProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class ProductServiceApplicationTests {
    @Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    @Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private ICategoryRepository categoryRepository;
	@DynamicPropertySource
	static void setProperties (DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);

	}

	@Test
	void shouldCreateCategory() throws Exception {
		CategoryDTO categoryDTO = getCategoryRequest();
		String categoryDTOtring = objectMapper.writeValueAsString(categoryDTO);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/category/new")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryDTOtring))
				.andExpect(status().isCreated());
		Assertions.assertTrue(categoryRepository.findAll().size()==1);
	}



	@Test
	void shouldCreateProduct() throws Exception {
		ProductDTO productDTO = getProductRequest();
		String productDTOString = objectMapper.writeValueAsString(productDTO);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product/new")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productDTOString))
				.andExpect(status().isCreated());
		Assertions.assertTrue(productRepository.findAll().size()==1);
	}

	@Test
	void shouldGetProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
				.andExpect(status().isOk());

	}

    private ProductDTO getProductRequest() {
		return ProductDTO.builder()
				.name("Franziscaner")
				.description("La cerveza frutal brumosa de color ámbar, Franziskaner Hefe-Weissbier Hell" +
						", es de cuerpo medio, refrescante y fresca, con sabor a plátanos, maracuyá, " +
						"especias, y malta. Esta cerveza de trigo es de fermentación alta es elaborada " +
						"en Bavaria, donde se sigue la receta producida desde 1363.")
				.price(BigDecimal.valueOf(16000))
				.imageURL("https://images/franziskaner.jpg")
				.categoryName("Weissbier")
				.build();
	}
	private CategoryDTO getCategoryRequest() {
		return CategoryDTO.builder()
				.categoryName("Weissbier")
				.categoryDescription("Las Weissbier, también llamadas Weizen son cervezas " +
						"de estilo ale o alta fermentación, que se elaboran con una alta " +
						"cantidad de maltas de trigo. Presentan un color pajizo pálido con" +
						" tendencia a dorado y suelen tener una capa de espuma espesa, " +
						"duradera y de color blanco.")
				.build();
	}

}
