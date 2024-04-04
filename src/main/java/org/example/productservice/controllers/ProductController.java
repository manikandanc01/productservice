package org.example.productservice.controllers;


import org.example.productservice.dto.GenericProductDto;
import org.example.productservice.dto.ValidateTokenRequestDto;
import org.example.productservice.exception.JwtVerificationException;
import org.example.productservice.exception.NotFoundException;
import org.example.productservice.security.SessionStatus;
import org.example.productservice.security.TokenValidator;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;
    private TokenValidator tokenValidator;

    @Autowired
    public ProductController(@Qualifier("fakestoreproductservice")ProductService productService,
                             TokenValidator tokenValidator)
    {
        this.productService =productService;
        this.tokenValidator=tokenValidator;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductsById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
                                             @PathVariable Long id) throws Exception
    {

        ValidateTokenRequestDto validateTokenRequestDto=new ValidateTokenRequestDto();
        validateTokenRequestDto.setToken(authToken);

         SessionStatus sessionStatus= tokenValidator.validateToken(validateTokenRequestDto);

         return productService.getProductById(id);
    }


    @PostMapping
    public GenericProductDto addNewProduct(@RequestBody GenericProductDto genericProductDto)
    {
        return productService.addNewProduct(genericProductDto);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto,@PathVariable Long id)
    {
       return productService.updateProductById(id,genericProductDto);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable Long id)
    {
        return productService.deleteProductById(id);
    }

}
