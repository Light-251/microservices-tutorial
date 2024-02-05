package com.silvio.productservice;//package com.silvio.productservice;

import com.silvio.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    public void contextLoader() {
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Product Test")
                .description("This is a test Product")
                .price(BigDecimal.valueOf(99999))
                .build();
    }

}
