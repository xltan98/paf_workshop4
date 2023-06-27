package sg.edu.nus.iss.paf_workshop4_jul2023.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_workshop4_jul2023.model.OrdersDetails;

@Repository
public class OrdersDetailsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String insertSQL= "insert into orders_details (product, unit_price, discount,quantity, order_id) values(?,?,?,?,?)";
// batch update will return 

    public int[] add(List<OrdersDetails> orderDetails){
        List<Object[]> params =orderDetails.stream()
        .map( od ->new Object[] {od.getProduct(),od.getUnitPrice(),od.getDiscount(),od.getQuantity(), od.getOrderId()})
        .collect(Collectors.toList());

        int added[] = jdbcTemplate.batchUpdate(insertSQL,params);

        return added;
        
    }
    
}
