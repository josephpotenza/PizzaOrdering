public class Order {
    private Integer quantity;
    private String orderName;
    private double orderPrice;



    public Order() {
    }

    public Order(int _quantity, String _orderName, double _orderPrice){
        quantity = _quantity;
        orderName = _orderName;
        orderPrice = _orderPrice;
    }

    public void plus1Quantity(){
        quantity++;
    }

    public void minus1Quantity(){
        quantity--;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "quantity=" + quantity +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
