import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cart extends Order {
    public int numOrders;
    private double totalPrice = 0;
    private double tax = 0;
    public ArrayList<Order> orders = new ArrayList<>();
    public ArrayList<Order> dbOrders = new ArrayList<>();
    public Order menu[] = new Order[30];
    public DecimalFormat df;
    private int dbnumorders = 0;

    public Cart() {
        for(int i = 0; i < 30; i++)
            menu[i] = new Order();
        getMenu();
        df = new DecimalFormat("#.00");
        df.format(totalPrice);
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
        if(checkIfPizza(order)){
            orders.add(order);
            numOrders++;
        }
        else{
            if(checkIfTopping(order)){
                orders.add(order);
                numOrders++;
            }
            else{
                if(existsInOrders(order)){      //if not pizza or toppings, and order does exist
                    int index = findIndexOfOrder(order);
                    orders.get(index).plus1Quantity();
                    orders.get(index).calcTotalPrice();
                }
                else{
                    orders.add(order);
                    numOrders++;
                }
            }
        }

        if(existsInDbOrders(order)) {
            dbOrders.get(findIndexOfOrderDB(order)).plus1Quantity();
            dbOrders.get(findIndexOfOrderDB(order)).calcTotalPrice();
        }
        else {
            Order temp = new Order(1, order.getOrderName(), order.getOrderPrice());
            dbOrders.add(temp);
        }
        dbnumorders = dbOrders.size();
        calcTotal();
        //viewDbOrders();

    }


    public void removeItem(int index){
        if(existsInDbOrders(orders.get(index))) {
            dbOrders.get(findIndexOfOrderDB(orders.get(index))).minus1Quantity();
            if(dbOrders.get(findIndexOfOrderDB(orders.get(index))).getQuantity() == 0)
                dbOrders.remove(findIndexOfOrderDB(orders.get(index)));
        }
        else {
            dbOrders.remove(findIndexOfOrderDB(orders.get(index)));
        }
        dbnumorders = dbOrders.size();

        if(checkIfPizza(index)){
            orders.remove(index);
            numOrders--;
        }
        else{
            if(checkIfTopping(index)){
                orders.remove(index);
                numOrders--;
            }
            else{
                if(orders.get(index).getQuantity() > 1){      //if not pizza or toppings, and order does exist
                    orders.get(index).minus1Quantity();
                    orders.get(index).calcTotalPrice();
                }
                else{
                    orders.remove(index);
                    numOrders--;
                }
            }
        }
        //orders.remove(index);
        //numOrders--;
        calcTotal();
        //viewDbOrders();
    }

    public void removeAll(){
        orders.clear();
        dbOrders.clear();
        numOrders = 0;
        dbnumorders = 0;
        totalPrice = 0;
        tax = 0;
    }

    public int getDbnumorders() {
        return dbnumorders;
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

    public Boolean checkIfPizza(Order order){
        for(int i = 0; i < 6; i++){
            if(menu[i].getOrderName() == order.getOrderName()){
                return true;
            }
        }
        return false;
    }


    public Boolean checkIfTopping(Order order){
        for(int i = 12; i < 18; i++){
            if(menu[i].getOrderName() == order.getOrderName()){
                return true;
            }
        }
        return false;
    }

    public Boolean checkIfTopping(int index){
        for(int i = 12; i < 18; i++){
            if(menu[i].getOrderName() == orders.get(index).getOrderName()){
                return true;
            }
        }
        return false;
    }


    private void calcTotal(){
        totalPrice = 0;
        for(int i = 0; i <  numOrders; i++){
            totalPrice = totalPrice + orders.get(i).getTotalPrice();
        }
        calcTax();
    }

    private void calcTax(){
        tax = totalPrice * .08875;
    }

    public double calcTotalPlusTax(){
        double total = totalPrice + tax;
        return total;
    }

    public double getTax(){
        return tax;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public int recentPizzaIndex(){
        for(int i = numOrders; i > 0; i--) {
            if(checkIfPizza(i-1)){
                return i-1;
            }
        }
        return -1;
    }

    public DecimalFormat getDf() {
        return df;
    }

    public Boolean existsInDbOrders(Order order){
        for(int i = 0; i < dbOrders.size(); i++){
            if(order.getOrderName() == dbOrders.get(i).getOrderName()){
                return true;
            }
        }
        return false;
    }

    public Boolean existsInOrders(Order order){
        for(int i = 0; i < orders.size(); i++){
            if(order.getOrderName() == orders.get(i).getOrderName()){
                return true;
            }
        }
        return false;
    }

    public int findIndexOfOrderDB(Order order){
        for(int i = 0; i < dbnumorders; i++){
            if(dbOrders.get(i).getOrderName() == order.getOrderName()){
                return i;
            }
        }
        return -1;
    }


    public int findIndexOfOrder(Order order){
        for(int i = 0; i < numOrders; i++){
            if(orders.get(i).getOrderName() == order.getOrderName()){
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

    public void viewDbOrders(){
        for(int i = 0; i < dbOrders.size(); i++){
            System.out.println(dbOrders.get(i));
        }
    }

    public int getNumOrders() {
        return numOrders;
    }


    public int findMenuID(int index) {
        for (int i = 0; i < 30; i++) {
            if (dbOrders.get(index).getOrderName() == menu[i].getOrderName()) {
                return i;
            }
        }
        return -1;
    }
    public int getQuantityofdbOrders(int index){
        return dbOrders.get(index).getQuantity();
    }
}