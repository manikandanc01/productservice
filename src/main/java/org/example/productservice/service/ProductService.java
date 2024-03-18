package org.example.productservice.service;

import org.example.productservice.dto.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

     List<GenericProductDto> getAllProducts();
     GenericProductDto getProductById(Long id);
     GenericProductDto addNewProduct(GenericProductDto genericProductDto);
     GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto);
     GenericProductDto deleteProductById(Long id);

}
