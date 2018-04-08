import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    static Connection connection = null;
    static Statement statement = null;
    static boolean connected = false;


    private static void connect() {
        try {

            String host = "jdbc:mysql://18.221.69.113:3306/pizzaOrdering";
            String uName = "root";
            String uPass = "csc430db";
            connection = DriverManager.getConnection(host, uName, uPass);
            statement = connection.createStatement();
            connected = true;
            // this is a working insertion method to insert user input to db.
            //String SQL = "Insert INTO customers(LastName, FirstName, phone, email) VALUES ('" + ln + "','" + fn + "','" + pn + "','" + em + "')";
           // stmt.executeUpdate(SQL);
           // String SQL = "SELECT * FROM customers";
             //ResultSet rs = statement.executeQuery(SQL);

           /* while (rs.next()) {

                int id_col = rs.getInt("customerID");
                String first_name = rs.getString("FirstName");
                String last_name = rs.getString("LastName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                System.out.println("Customer ID: " + id_col + " Name: " + first_name + " " + last_name);
               */// System.out.println("Email: " + email + " Phone: " + phone);

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public static void executeQuery(String SQL){
        try {
            if (!connected) {
                connect();
            }

            statement.executeUpdate(SQL);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }



    public static ResultSet getResult(String SQL) {
        try {
            if (!connected) {
                connect();
            }
            ResultSet resultSet = statement.executeQuery(SQL);


            return resultSet;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
