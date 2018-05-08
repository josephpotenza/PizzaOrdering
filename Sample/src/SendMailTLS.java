import java.util.Properties;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS extends Pizza{






    public SendMailTLS (){
       Customer customer = new Customer();
       //firstName = customer.getFirstName();
      // email = customer.getEmail();

    }

    public void sendEmail(){
         final String username = "MCVJ.PizzaOrdering@gmail.com";
         final String password = "pizzaOrdering";



        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));


        String email = "christian10305@gmail.com";
        String firstName = "Joe";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("MCVJ.PizzaOrdering@gmail.com")); // same email id
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("" + email + ""));// whome u have to send mails that person id
            message.setSubject("Thank You For Placing an Order with us! Below is your Order Receipt!");
            String context;
            context = "..........."+ "\n"+
                    dateFormat.format((date)) + "\n" +
                    "............." + "\n" +
                    "Dear "+ firstName  +" ,Below is your order receipt " + "\n";
                    for(int i = 0; i < cart.getNumOrders(); i++){
                        context = context + cart.getOrderName().toString();
            }
                    context = context + "\n\n No spam to my email, please!";

            message.setText(context);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



}
