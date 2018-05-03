import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cart extends Order {
   private int numOrders;
   private double totalPrice = 0;
    public ArrayList<Order> orders = new ArrayList<>();
    public Order menu[] = new Order[30];


    public Cart() {
        for(int i = 0; i < 30; i++)
                menu[i] = new Order();
        getMenu();

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
            calcTotal();
       }


       public void removeItem(int index){
            orders.remove(index);
            numOrders--;
            calcTotal();
       }
        public Order getOrder(int index){
            return orders.get(index);
        }


        public int checkIfToppingsExist(int index){
            int numToppings = 0;
            for(int j = 1; j < 4; j++) {        //max of 3 toppings
                if (checkIfPizza(index)) {
                    if(index + j >= numOrders)
                        return numToppings;
                    else {
                        if (checkIfPizza(index + j)) {
                            break;
                        } else {
                            for (int i = 12; i < 18; i++) {
                                if (menu[i].getOrderName() == orders.get(index + j).getOrderName()) {
                                    numToppings++;
                                }
                            }
                        }
                    }
                }
            }
            return numToppings;
        }


        public Boolean checkIfPizza(int index){
            for(int i = 0; i < 6; i++){
                if(menu[i].getOrderName() == orders.get(index).getOrderName()){
                    return true;
                }
            }
            return false;
        }

        //check if order name exist in orders array
        // if true quantity++, else do nothing
        public void orderExist(Order order){
            if(orders.contains(order)){
                quantity++;
            }
        }


        public void calcTotal(){
        totalPrice = 0;
            for(int i = 0; i <  numOrders; i++){
                totalPrice = totalPrice + orders.get(i).getOrderPrice() * orders.get(i).getQuantity();
            }
        }

        public double getTotalPrice(){
            return totalPrice;
        }


    public int getNumOrders() {
        return numOrders;
    }
}


