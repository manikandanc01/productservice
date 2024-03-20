package org.example.productservice.service;

import org.example.productservice.dto.GenericProductDto;
import org.example.productservice.exception.NotFoundException;
import org.example.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import org.example.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("fakestoreproductservice")

public class FakeStoreProductService implements ProductService{

    private FakeStoreProductClient fakeStoreProductClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient)
    {
        this.fakeStoreProductClient=fakeStoreProductClient;
    }

    public GenericProductDto convertFakeStoreToGenericProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto genericProductDto=new GenericProductDto();

        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());

        return genericProductDto;
    }
    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> genericProductDtos=new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductClient.getAllProducts())
        {
            genericProductDtos.add(convertFakeStoreToGenericProduct(fakeStoreProductDto));
        }
        return genericProductDtos ;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreToGenericProduct(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDto addNewProduct(GenericProductDto genericProductDto) {

        return convertFakeStoreToGenericProduct(fakeStoreProductClient.addNewProduct(genericProductDto));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {


        return convertFakeStoreToGenericProduct(fakeStoreProductClient.updateProductById(id,genericProductDto));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {


        return convertFakeStoreToGenericProduct(fakeStoreProductClient.deleteProductById(id));
    }
}
