import com.sun.tools.corba.se.idl.constExpr.Or;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Cart extends Order {
   public int numOrders;
   public double costPerOrder = 0;
   private double totalPrice = 0;
    public ArrayList<Order> orders = new ArrayList<>();
    public Order menu[] = new Order[30];


    public Cart() {
        for(int i = 0; i < 30; i++)
                menu[i] = new Order();


    }
   public void getMenu(){
        try {
            String SQL = "SELECT * FROM menu";
            ResultSet result = Database.getResult(SQL);
            int temp = 0;
            while (result.next()) {
                menu[temp].setOrderName(result.getString("itemName"));
                menu[temp].setOrderPrice(result.getDouble("price"));
                temp++;
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage());

        }
       }


       public void addToCart(Order order){
            orders.add(order);
            numOrders++;
       }
        public Order getOrder(int index){

        return orders.get(index);
        }


        //check if order name exist in orders array
        // if true quantity++, else do nothing
        public void orderExist(Order order){
            if(orders.contains(order)){
                quantity++;
            }
        }



    }


