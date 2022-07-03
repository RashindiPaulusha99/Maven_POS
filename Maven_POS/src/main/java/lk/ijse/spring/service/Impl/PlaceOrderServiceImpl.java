package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.OrderDTO;
import lk.ijse.spring.entity.Order;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailRepo;
import lk.ijse.spring.repo.OrderRepo;
import lk.ijse.spring.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void placeOrder(OrderDTO orderDTO) {

    }

    @Override
    public void deleteOrder(String oid) {
        if (orderRepo.existsById(oid)){
            orderRepo.deleteById(oid);
        }else {
            throw new RuntimeException(oid +" "+ "No Such Order..! Please Check The Correct OrderId..!");
        }
    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {

    }

    @Override
    public OrderDTO searchOrder(String oid) {
        if (orderRepo.existsById(oid)){
            Order order = orderRepo.findById(oid).get();
            return modelMapper.map(order, OrderDTO.class);
        }else {
            throw new RuntimeException(oid + " " + "No Such Order..! Please Check The OrderId..!");
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> all = orderRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<OrderDTO>>(){
        }.getType());
    }

    @Override
    public String generateOrderId() {
        return orderRepo.generateOrderId();
    }
}
