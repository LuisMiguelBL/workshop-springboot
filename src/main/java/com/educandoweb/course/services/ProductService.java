package com.educandoweb.course.services;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.exceptions.ProductNotFoundException;
import com.educandoweb.course.entities.exceptions.UserNotFoundException;
import com.educandoweb.course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Produto não encontrado pelo id: "+ id));
    }

}
