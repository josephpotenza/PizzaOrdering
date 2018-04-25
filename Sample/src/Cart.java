import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Cart extends Order {
    public int numOrders;
    public double totalPrice;
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
            calcTotalPrice();
       }

        public Order getOrder(int index){
            return orders.get(index);
        }

        public void calcTotalPrice(){
            totalPrice = 0;
            for(int i = 0; i < numOrders; i++){
                totalPrice = totalPrice + orders.get(i).getOrderPrice();
            }
        }

        public Double getTotalPrice(){
            return totalPrice;
        }
}


