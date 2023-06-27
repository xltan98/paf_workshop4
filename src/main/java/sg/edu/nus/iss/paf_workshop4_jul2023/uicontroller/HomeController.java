package sg.edu.nus.iss.paf_workshop4_jul2023.uicontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.paf_workshop4_jul2023.model.Orders;
import sg.edu.nus.iss.paf_workshop4_jul2023.model.OrdersDetails;



@Controller
@RequestMapping("/home")
public class HomeController {

   @Value("${home.title}")
    private String homeTitle;
    @GetMapping

    public String welcome(Model model){
        model.addAttribute("homeTitle",homeTitle);
        return "welcome";

    }
    @GetMapping("/orderadd")
    public String newOrderForm(Model model){
        Orders order= new Orders();
        model.addAttribute("order",order);
        return "orderadd";
    }

    @PostMapping("/ordernext")
    public String postOrderForm(HttpSession session,@ModelAttribute("order")Orders order){
        //System.out.print("homeController>postorderform>"+order);
        session.setAttribute("order",order);
        return "redirect:/home/orderdetailadd";
    }
    @GetMapping("/orderdetailadd")
    public String newOrderDetailForm(Model model){
        OrdersDetails orderDetails = new OrdersDetails();
        model.addAttribute("orderDetails", orderDetails);

        return "orderdetailadd";

    }
    @PostMapping("/nextdetails")
    public String postOrderDetails(HttpSession session, @ModelAttribute("orderDetails") OrdersDetails orderDetails, Model model){
        OrdersDetails newOrderDetails = new OrdersDetails();
         model.addAttribute("orderDetails",newOrderDetails);

   

    List<OrdersDetails> ordDetailList=null;
    if(session.getAttribute("orderdetails")==null){
        ordDetailList= new ArrayList<OrdersDetails>();
        ordDetailList.add(orderDetails);
        session.setAttribute("orderdetails",ordDetailList);

    }else{
        ordDetailList=(List<OrdersDetails>)session.getAttribute("orderdetails");
        ordDetailList.add(orderDetails);
        session.setAttribute("orderdetails", newOrderDetails);
    }

     System.out.print("homeController>postorderform>"+session.getAttribute("order"));
    System.out.print("homeController>postorderform>"+newOrderDetails);
    //OrdersDetails newOrderDetail= new OrdersDetails();
   

    return "orderdetailadd";
    }
}
