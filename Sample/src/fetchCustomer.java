import java.sql.ResultSet;
import java.sql.SQLException;

public class fetchCustomer {

    Customer customer_;

    public fetchCustomer() {
    }

    public void fetchCustomerInfo(String email){
        String SQL = "SELECT * FROM customers WHERE email = '" + email;
        ResultSet result = Database.getResult(SQL);
        try {
            customer_.setFirstName(result.getString("firstName"));
            customer_.setLastName(result.getString("lastName"));
            customer_.setPass(result.getString("password"));
            customer_.setAddress(result.getString("address"));
            customer_.setCreditCard(result.getString("creditCard"));
            customer_.setPhone(result.getString("phone"));
            customer_.setEmail(email);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
