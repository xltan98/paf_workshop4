package sg.edu.nus.iss.paf_workshop4_jul2023.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.nus.iss.paf_workshop4_jul2023.model.Orders;
import sg.edu.nus.iss.paf_workshop4_jul2023.model.OrdersDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    
    private Orders order;
    private List<OrdersDetails> orderDetails;
    
}
