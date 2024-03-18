package org.example.productservice.thirdpartyclients.fakestore;

import org.example.productservice.dto.GenericProductDto;
import org.example.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class FakeStoreProductClient {

    private String productBaseUrl;
    private String productPath;
    private String finalProductUrl;
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}")String productBaseUrl,
                                  @Value("${fakestore.api.productpath}")String productPath)
    {
        this.restTemplateBuilder=restTemplateBuilder;
        this.finalProductUrl=productBaseUrl+productPath;
    }
    public FakeStoreProductDto getProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto= restTemplate.getForEntity(finalProductUrl+"/"+id,FakeStoreProductDto.class);


        return fakeStoreProductDto.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> responseEntity=restTemplate.getForEntity(finalProductUrl,FakeStoreProductDto[].class);

        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(responseEntity.getBody())));
    }

    public FakeStoreProductDto addNewProduct(GenericProductDto genericProductDto) {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =restTemplate.postForEntity(finalProductUrl,genericProductDto,FakeStoreProductDto.class);

        return fakeStoreProductDto.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto genericProductDto) {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= restTemplate.exchange(finalProductUrl+"/"+id, HttpMethod.PUT,new HttpEntity<>(genericProductDto),FakeStoreProductDto.class);
        return fakeStoreProductDtoResponseEntity.getBody();
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=restTemplate.exchange(finalProductUrl+"/"+id,HttpMethod.DELETE,null,FakeStoreProductDto.class);
        return fakeStoreProductDtoResponseEntity.getBody();
    }
}
