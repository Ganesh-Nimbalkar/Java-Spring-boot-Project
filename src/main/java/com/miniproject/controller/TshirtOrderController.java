// TshirtOrderController.java
package com.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.miniproject.model.TshirtOrder;
import com.miniproject.service.TshirtOrderService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class TshirtOrderController {

    @Autowired
    private TshirtOrderService orderService;

    @PostMapping("/submit")
    public ResponseEntity<TshirtOrder> submitOrder(@RequestParam("image") MultipartFile file,
                                                    @RequestParam("fullname") String fullname,
                                                    @RequestParam("contact") String contact,
                                                    @RequestParam("color") String color,
                                                    @RequestParam("size") String size,
                                                    @RequestParam("quantity") int quantity,
                                                    @RequestParam("message") String message) {
        try {
            // Process the form data and save the order
            String imagePath = "C:\\Users\\91879\\Desktop\\imges\\ganeshs" + file.getOriginalFilename();
            file.transferTo(new File(imagePath));

            TshirtOrder order = new TshirtOrder();
            order.setFullname(fullname);
            order.setContact(contact);
            order.setColor(color);
            order.setSize(size);
            order.setQuantity(quantity);
            order.setImagePath(imagePath);
            order.setMessage(message); // Set the message

            // Save the order to the database (assuming you have a method to do this)
            orderService.saveOrder(order);

            // Return a success response with the saved order
            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






//    @GetMapping("/all")
//    public List<TshirtOrder> getAllOrders() {
//        return orderService.getAllOrders();
//    }
    @GetMapping("/all")
    public List<TshirtOrder> getAllOrders() {
        List<TshirtOrder> orders = orderService.getAllOrders();
        for (TshirtOrder order : orders) {
            order.setImagePath("http://localhost:8082/orders/image/" + order.getId());
        }
        return orders;
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        // Fetch the TshirtOrder object by ID to get the image path
        TshirtOrder order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            // Read the image file into a byte array
            byte[] imageBytes = Files.readAllBytes(Paths.get(order.getImagePath()));

            // Set the appropriate content type for the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Assuming the image type is JPEG

            // Return the byte array along with the appropriate headers
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Handle the exception if the file cannot be read
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<TshirtOrder> getOrderById(@PathVariable Long id, @RequestParam(required = false) String fullname) {
        TshirtOrder order;
        if (fullname != null) {
            order = orderService.getOrderByIdAndFullname(id, fullname);
        } else {
            order = orderService.getOrderById(id);
        }
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<TshirtOrder> updateOrder(@PathVariable Long id, @RequestBody TshirtOrder orderDetails) {
        TshirtOrder updatedOrder = orderService.updateOrder(id, orderDetails);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        String result = orderService.deleteOrder(id);
        if (result.equals("deleted")) {
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
