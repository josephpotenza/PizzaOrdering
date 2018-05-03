import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cart extends Order {
    public int numOrders;
    private double totalPrice = 0;
    public ArrayList<Order> orders = new ArrayList<>();
    public ArrayList<Order> dbOrders = new ArrayList<>();
    public Order menu[] = new Order[30];
    public DecimalFormat df;

    public Cart() {
        for (int i = 0; i < 30; i++)
            menu[i] = new Order();
        getMenu();
        df = new DecimalFormat("#.00");
        df.format(totalPrice);
    }

    public void getMenu() {
        try {
            String SQL = "SELECT * FROM menu";
            ResultSet result = Database.getResult(SQL);
            int temp = 0;
            while (result.next()) {
                menu[temp].setOrderName(result.getString("itemName"));
                menu[temp].setOrderPrice(result.getDouble("price"));
                temp++;
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());

        }
    }


    public void addToCart(Order order) {
        orders.add(order);
        if (existsInDbOrders(order)) {
            dbOrders.get(findIndexOfOrder(order)).plus1Quantity();
        } else
            dbOrders.add(order);
        numOrders++;
        calcTotal();
    }


    public void removeItem(int index) {
        orders.remove(index);
        if (existsInDbOrders(orders.get(index))) {
            dbOrders.get(index).minus1Quantity();
        } else
            dbOrders.remove(index);
        numOrders--;
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }


    public int checkIfToppingsExist(int index) {
        int numToppings = 0;
        for (int j = 1; j < 4; j++) {        //max of 3 toppings
            if (checkIfPizza(index)) {
                if (index + j >= numOrders)
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


    public Boolean checkIfPizza(int index) {
        for (int i = 0; i < 6; i++) {
            if (menu[i].getOrderName() == orders.get(index).getOrderName()) {
                return true;
            }
        }
        return false;
    }


    public void calcTotal() {
        totalPrice = 0;
        for (int i = 0; i < numOrders; i++) {
            totalPrice = totalPrice + orders.get(i).getOrderPrice() * orders.get(i).getQuantity();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int recentPizzaIndex() {
        for (int i = numOrders; i > 0; i--) {
            if (checkIfPizza(i - 1)) {
                return i - 1;
            }
        }
        return -1;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public Boolean existsInDbOrders(Order order) {
        for (int i = 0; i < numOrders; i++) {
            if (order.getOrderName() == orders.get(i).getOrderName()) {
                return true;
            }
        }
        return false;
    }

    public int findIndexOfOrder(Order order) {
        for (int i = 0; i < numOrders; i++) {
            if (dbOrders.get(i).getOrderName() == order.getOrderName()) {
                return i;
            }
        }
        return -1;
    }

   /*
    public void countQuantity(int index){
        for(int i = 0; i < numOrders; i++){
            if(orders.get(i).getOrderName() == orders.get(index).getOrderName()){
                orders.get(index).plus1Quantity();
            }
        }
    }
    */

    public void viewDbOrders() {
        for (int i = 0; i < dbOrders.size(); i++) {
            System.out.println(dbOrders.get(i));
        }
    }

    public int getNumOrders() {
        return numOrders;
    }

    public int findMenuID(int index) {
        for (int i = 0; i < 30; i++) {
            if (orders.get(index).getOrderName() == menu[i].getOrderName()) {
                return i;
            }
        }
        return -1;
    }
    public int getQuantityofdbOrders(int index){
        return dbOrders.get(index).getQuantity();
    }


}