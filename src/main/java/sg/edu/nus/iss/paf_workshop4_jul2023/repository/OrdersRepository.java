package sg.edu.nus.iss.paf_workshop4_jul2023.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_workshop4_jul2023.exception.ResourceNotFoundException;
import sg.edu.nus.iss.paf_workshop4_jul2023.model.Orders;

@Repository
public class OrdersRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL="select * from orders";
    private final String findByIdSQL="select * from orders where order_id=?";
    private final String insertSQL = "insert into orders (order_date,customer_name,ship_address, notes, tax) values(?, ?, ?, ?, ?)";

    public List<Orders> getAllOrders(){
        List<Orders> orderList = new ArrayList<>();
        orderList= jdbcTemplate.query(findAllSQL,BeanPropertyRowMapper.newInstance(Orders.class));
         if (orderList.isEmpty()){
            throw new ResourceNotFoundException("Order not found");
        }
        return orderList;
    }

    public Orders findOrderById(Integer id){
        List<Orders> orderList = new ArrayList<>();
        orderList= jdbcTemplate.query(findByIdSQL,BeanPropertyRowMapper.newInstance(Orders.class),id);
        
        if (orderList.isEmpty()){
            throw new  ResourceNotFoundException("Order not found");
        }

        return orderList.get(0);
    }

    public Boolean createOrder(Orders order){

        // must follow sequence
        Integer iResult = jdbcTemplate.update(insertSQL,order.getOrderDate(),order.getCustomerName(), order.getShipAddress(), order.getNotes(),order.getNotes());

        return iResult>0?true:false;
    }

    public Integer insertOrder(Orders order){
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator(){

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSQL,new String[] {"order_id"});
                ps.setDate(1,order.getOrderDate());
                ps.setString(2,order.getCustomerName());
                ps.setString(3,order.getShipAddress());
                ps.setString(4,order.getNotes());
                ps.setFloat(5,order.getTax());
                return ps;
            }
            
        };
        jdbcTemplate.update(psc,generatedKeyHolder);
        // possible to be save a String as well, it is possible that your primary key is a String 

        Integer iResultValue = generatedKeyHolder.getKey().intValue();
        return iResultValue;
    }






    
}
