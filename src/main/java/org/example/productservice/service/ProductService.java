package org.example.productservice.service;

import org.example.productservice.dto.GenericProductDto;
import org.example.productservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

     List<GenericProductDto> getAllProducts();
     GenericProductDto getProductById(Long id) throws NotFoundException;
     GenericProductDto addNewProduct(GenericProductDto genericProductDto);
     GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto);
     GenericProductDto deleteProductById(Long id);

}
