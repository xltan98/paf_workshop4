package sg.edu.nus.iss.paf_workshop4_jul2023.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf_workshop4_jul2023.model.Orders;
import sg.edu.nus.iss.paf_workshop4_jul2023.model.OrdersDetails;
import sg.edu.nus.iss.paf_workshop4_jul2023.repository.OrdersDetailsRepository;
import sg.edu.nus.iss.paf_workshop4_jul2023.repository.OrdersRepository;

@Service
public class OrderService {

    @Autowired
    OrdersRepository oRepo;

    @Autowired
    OrdersDetailsRepository odRepo;

    @Transactional
    public Boolean makeOrder(Orders order, List<OrdersDetails> details){
        //simulate error before performing any operations

        // if(true){
        //     throw new IllegalArgumentException("Exception before performing any operation");
        // }

        // at this point the list of details will have null order_id aka the foreign key
        // return primary key i (which is the id)

        //1. create the order and get the returned pk of the created order
        Integer iCreatedOrderId=oRepo.insertOrder(order);
        //simulate error after creating a new order
        // if(true){
        //     throw new IllegalArgumentException("Exception after creating a new order");
        // }

        // 2. set the fk for the order details that linked to the created order
        for(OrdersDetails od:details){
            od.setOrderId(iCreatedOrderId);
        }
        //3. create the order details
        odRepo.add(details);

        //simulate order at the end of transaction after performing 2. & 3

        return true;
    }

    
}
