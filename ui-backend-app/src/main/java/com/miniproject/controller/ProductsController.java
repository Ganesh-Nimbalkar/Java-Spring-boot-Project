package com.miniproject.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.miniproject.model.ProductsModel;
import com.miniproject.service.ProductsService;



@RestController
@CrossOrigin("*")
public class ProductsController {

    @Autowired
    ProductsService aps;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestParam("name") String name, @RequestParam("brand") String brand,
                                             @RequestParam("price") int price, @RequestParam("image") MultipartFile image) {
        String product = aps.addProduct(name, brand, price, image);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getAllProductsWithoutImage")
    public List<ProductsModel> getAllProductsWithoutImage() {
        return aps.getAllProductsWithoutImage();
    }

    @GetMapping("/getProductimageById/{productId}")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable Long productId) {
        ProductsModel product = aps.getProductById(productId);
        if (product != null && product.getImage() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(product.getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set appropriate content type
            headers.setContentLength(imageBytes.length);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getByNameOrBrand")
    public List<ProductsModel> findByNameOrBrand(@RequestParam("data") String data) {
        return aps.findByNameOrBrand(data);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        aps.deleteProduct(productId);
    }

    @PutMapping("/updateProduct/{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestParam String name, @RequestParam String brand,
                                @RequestParam Integer price, @RequestParam MultipartFile image) {
        return aps.updateProduct(productId, name, brand, price, image);
    }
}
