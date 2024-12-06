package com.sminfinitetech.thrivesonke.features.pos.controller;

import com.sminfinitetech.thrivesonke.features.pos.dto.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pos")
public class PosController {

   @GetMapping("/hello")
    public String helloWorld(){
       return "Hello, Word";
   }

   @PostMapping("/add")
    public String addProduct( @RequestBody String product){
       return "Added the product " + product;
   }

    @PostMapping("/post-order")
    public String order(@RequestBody Order order) {
//        if (order == null || order.getProductName() == null || order.getOrderNum() == null) {
//            return "Invalid order details";
//        }
        return "Successfully placed the order for " + order.getProductName() +
                ". Your order number is " + order.getOrderNum();
    }
}
