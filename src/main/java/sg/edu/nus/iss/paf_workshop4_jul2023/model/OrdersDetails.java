package sg.edu.nus.iss.paf_workshop4_jul2023.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersDetails {

    private Integer id;
    private Integer orderId;
    private String product;
    private Float unitPrice;
    private Float discount;
    private Integer quantity;
    
}
