package sg.edu.nus.iss.paf_workshop4_jul2023.model;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {

private Integer orderId;
private Date orderDate;
private String customerName;
private String shipAddress;
private String notes;
private float tax;


    
}
