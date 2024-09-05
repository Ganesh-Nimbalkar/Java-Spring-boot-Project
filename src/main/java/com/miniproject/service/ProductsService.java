package com.miniproject.service;

import java.util.Base64;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.miniproject.dao.ProductsRepository;
import com.miniproject.model.ProductsModel;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ProductsService {

    @Autowired
    ProductsRepository apr;

    public String addProduct(String name, String brand, int price, MultipartFile image) {        ProductsModel apm = new ProductsModel();
        apm.setName(name);
        apm.setBrand(brand);
        apm.setPrice(price);
        try {
            apm.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        apm.setDatetime(new Date());
        apr.save(apm);
        return "1 product has been added successfully";
    }

    public List<ProductsModel> getAllProductsWithoutImage() {
        List<ProductsModel> products = apr.findAll();
        for (ProductsModel product : products) {
            // Set the image field to null to exclude it from the response
            product.setImage(null);
        }
        return products;
    }


    public void deleteProduct(Long productId) {
        apr.deleteById(productId);
    }

    public ProductsModel getProductById(Long productId) {
        return apr.findById(productId).orElse(null);
    }

    public String updateProduct(Long productId, String name, String brand, int price, MultipartFile image) {
        ProductsModel apm = apr.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        apm.setName(name);
        apm.setBrand(brand);
        apm.setPrice(price);
        apm.setDatetime(new Date());
        try {
            apm.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        apr.save(apm);
        return "1 product has been updated successfully";
    }

    public List<ProductsModel> findByNameOrBrand(String data) {
        return apr.findByNameOrBrand(data, data);
    }
}

