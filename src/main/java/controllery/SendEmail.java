package controllery;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import  javax.servlet.annotation.WebServlet;
@WebServlet(name = "SendEmail")
public class SendEmail extends HttpServlet {

    static final String SENDER_EMAIL_ADDRESS = "satiks@ukr.net";
    static final String SENDER_EMAIL_PASSWORD = "";
    static final String SENDER_HOST = "sctp.ukr.net";
    static final String SENDER_PORT = "5000";
    static final String RECEIVER_EMAIL_ADDRESS = "satiks@ukr.net";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Properties properties = new Properties();
        properties.put("mail.sctp.host", SENDER_HOST);
        properties.put("mail.sctp.port", SENDER_PORT);
        properties.put("mail.from", SENDER_EMAIL_ADDRESS);
        properties.put("mail.sctp.password", SENDER_EMAIL_PASSWORD);
        properties.put("mail.sctp.auth", "true");
        properties.put("mail.sctp.startles.enable", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
                    }
                });

        response.setContentType("text/html");

        String docType = "<!DOCTYPE html>";
        String title = "Send Email ";

        PrintWriter writer = response.getWriter();

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(RECEIVER_EMAIL_ADDRESS));
            message.setSubject("Convert Servlets Tutorial");
            message.setText("Send Email ");
            Transport.send(message);
            String sendEmailResultMessage = "Email is successfully sent!";
            writer.println(docType + "<html>" +
                    "<head>" +
                    "<title>" + title + "</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>" + sendEmailResultMessage + "</h1>" +
                    "</body>" +
                    "</html>");


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}



















