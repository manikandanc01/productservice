package org.example.productservice.controllers;


import org.example.productservice.dto.GenericProductDto;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("fakestoreproductservice")ProductService productService)
    {
        this.productService =productService;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductsById(@PathVariable Long id)
    {
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
