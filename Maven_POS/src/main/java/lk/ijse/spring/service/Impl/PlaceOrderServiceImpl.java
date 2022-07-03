package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.OrderDTO;
import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.Order;
import lk.ijse.spring.entity.OrderDetail;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailRepo;
import lk.ijse.spring.repo.OrderRepo;
import lk.ijse.spring.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        System.out.println(orderDTO+"   service");

        ArrayList<OrderDetailsDTO> orderDetails = (ArrayList<OrderDetailsDTO>) orderDTO.getOrderDetails();
        orderDTO.setOrderDetails(orderDetails);

        Order order = modelMapper.map(orderDTO, Order.class);
        if (!orderRepo.existsById(orderDTO.getOrderId())){

            if (orderDTO.getOrderDetails().size() < 1){
                throw new RuntimeException("No Items In Order..!");
            }else {
                System.out.println(order);
                orderRepo.save(order);

                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    System.out.println(orderDetail);
                    orderDetailRepo.save(orderDetail);
                    Item item = itemRepo.findById(orderDetail.getItemId()).get();
                    item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getSellQty());
                    itemRepo.save(item);
                }
            }

        }else {
            throw  new RuntimeException(orderDTO.getOrderId()+" "+"Order Already Exist..!");
        }
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
