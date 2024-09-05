// TshirtOrderService.java
package com.miniproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miniproject.dao.TshirtOrderRepository;
import com.miniproject.model.TshirtOrder;
import java.util.List;

@Service
public class TshirtOrderService {

    @Autowired
    private TshirtOrderRepository orderRepository;


    public TshirtOrder saveOrder(TshirtOrder order) {
        return orderRepository.save(order);
    }
    public List<TshirtOrder> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public TshirtOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public TshirtOrder getOrderByIdAndFullname(Long id, String fullname) {
        return orderRepository.findByIdAndFullname(id, fullname);
    }
    public TshirtOrder updateOrder(Long id, TshirtOrder orderDetails) {
        TshirtOrder order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            // Update order details here
            // For simplicity, assuming setters are available in TshirtOrder model
            order.setFullname(orderDetails.getFullname());
            order.setContact(orderDetails.getContact());
            order.setColor(orderDetails.getColor()); // Update color
            order.setSize(orderDetails.getSize()); // Update size
            order.setQuantity(orderDetails.getQuantity()); // Update quantity
            // Update other fields as needed
            return orderRepository.save(order);
        }
        return null;
    }


    public String deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return "deleted";
        }
        return "not found";
    }
}
