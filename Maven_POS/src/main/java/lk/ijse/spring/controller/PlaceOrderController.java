package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.OrderDTO;
import lk.ijse.spring.entity.Order;
import lk.ijse.spring.service.PlaceOrderService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("order")
@CrossOrigin
public class PlaceOrderController {

    @Autowired
    PlaceOrderService placeOrderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllOrders(){
        return new ResponseUtil(200,"Ok",placeOrderService.getAllOrders());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveOrder(@RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO+"   controller");
        System.out.println(orderDTO.getOrderDetails());
        placeOrderService.placeOrder(orderDTO);
        return new ResponseUtil(200, "Successfully Purchased Order.",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchOrder(@PathVariable String id){
        return new ResponseUtil(200, "Ok.",placeOrderService.searchOrder(id));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateOrder(@RequestBody OrderDTO orderDTO){
        placeOrderService.updateOrder(orderDTO);
        return new ResponseUtil(200, "Order Successfully Updated.",null);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteOrder(@RequestParam String id){
        placeOrderService.deleteOrder(id);
        return new ResponseUtil(200, "Order Successfully Deleted.", null);
    }

    @GetMapping(params = {"test"})
    public ResponseUtil generateOrderId(@RequestParam String test) {
        return new ResponseUtil(200, "Ok", placeOrderService.generateOrderId());
    }

}
