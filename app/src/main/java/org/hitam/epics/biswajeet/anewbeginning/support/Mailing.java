package org.hitam.epics.biswajeet.anewbeginning.support;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.hitam.epics.biswajeet.anewbeginning.CheckoutActivity;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by biswajeet on 23/1/17.
 */

public class Mailing {
    static Message message;
    static String MailContentPart1 = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head><body>" +
            "<div style=\"margin:0 auto;padding:0\">" +
            "<div style=\"background:#ffffff\">" +
            "<table style=\"position:relative\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">" +
            "<tr>" +
            "<td>" +
            "<div style=\"border:1px solid #eaeaea;width:452px;float:left\">" +
            "<div style=\"width:100%;margin:auto;padding:27px 0 30px 0;overflow:hidden\">" +
            "<span style=\"color:#00b0de;font-weight:600\">A New Beggining for homeless</span>" +
            "</div>" +
            "<div style=\"background:#f7fcfe;color:#5b5b5b;font-family:&#39;Open Sans&#39;;font-size:13px;line-height:17px;clear:both;padding:35px;float:left;margin-bottom:45px\">" +
            "<div>" +
            "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" +
            "<tr>" +
            "<td style=\"width:48px\"><img src=\"https://s3-ap-southeast-1.amazonaws.com/assets.paytm.com/promotion/Aziz/folder/wallet-statement.png\" style=\"float:left\"></td>" +
            "<td>" +
            "<div style=\"float:left;margin:2px 0 0 26px;font-size:22px;font-weight:100;line-height:29px\"><span style=\"font-weight:400\">Donation Order</span> for <br/><span style=\"color:#00b0de;font-weight:600\">A New Begining for homeless</span>" +
            "<br/><span style=\"color:#000000;font-weight:600;font-size:12px\">";

    static String MailContentPart2 = "</span></div></td></tr>" +
            "</table>" +
            "</div>" +
            "<p style=\"font-size:14px;padding:30px 0 14px 0;margin:0;clear:both;border-bottom:1px solid #ececec;width:100%;float:left\">" +
            "<span style=\"float:left\"><b>Item</b></span>" +
            "<span style=\"float:right;width:20%;text-align:left\"><b>Price</b></span>" +
            "<span style=\"float:right;width:30%;text-align:center\"><b>Quantity</b></span>" +
            "</p>";

    static String BillItem1 = "<p style=\"font-size:14px;padding:13px 0 14px 0;margin:0;clear:both;border-bottom:1px solid #ececec;width:100%;float:left\"><span style=\"float:left\">";
    static String BillItem2 = "</span><span style=\"float:right;width:20%;text-align:left\">Rs.";
    static String BillItem3 = "</span><span style=\"float:right;width:30%;text-align:center\">";
    static String BillItem4 = "</span></p>";

    static String Total1 = "<p style=\"font-size:14px;padding:13px 0 0 0;margin:0;clear:both;width:100%;float:left\">" +
            "<span style=\"float:left\"><b>Total Cost</b></span>" +
            "<span style=\"float:right;width:20%;text-align:left\"><b>Rs.";
    static String Total2 = "</b></span></p>";

    static String MailEnd = "<p style=\"font-size:14px;padding:13px 0 0 0;margin:0;clear:both;width:100%;float:center\">" +
            "<br/>" +
            "<span style=\"float:left;width:100%;text-align:center\">Thank you for your involvement in the good cause.</span>" +
            "</p>" +
            "</div>" +
            "</div>" +
            "</td>" +
            "</tr>" +
            "</table>" +
            "</div>" +
            "</div>" +
            "</body></html>";

    static String Bill = "";
    public static String userMail = null;

    public static void makePaymentBill() {


        Bill = "";
        for (CheckoutItem item :
                CheckoutActivity.CheckOutList) {
            Bill += BillItem1;
            Bill += item.getName();
            Bill += BillItem2;
            Bill += (item.getPrice() * item.getQuantity());
            Bill += BillItem3;
            Bill += item.getQuantity();
            Bill += BillItem4;
        }
        Bill += (Total1 + CheckoutActivity.calculateTotal() + Total2);
    }

//    public static String vendorMail = "";
    public static String organisationMail = "Anewbeginningforhomeless@gmail.com";

    public static void sendPaymentIntialisingMail() {
        String Content = MailContentPart1 +
                MailContentPart2 +
                Bill +
                MailEnd;

        String to = organisationMail+",order.anbfh@gmail.com";//change accordingly
        sendMail(Content, to, "Payment Initialisation Started"); // In case payment is made but confirmation mail got error. This will be backup for bill
    }

    public static void sendPaymentConfirmationMail(String paymentid) {
        String Content = MailContentPart1 +
                "Payment Id: " +
                paymentid +
                MailContentPart2 +
                Bill +
                MailEnd;

        String to = organisationMail+",order.anbfh@gmail.com";//change accordingly
//        Log.e("sendMail: ", userMail);
        if (userMail != null) {
            to += ("," + userMail);
        }
        sendMail(Content, to, "Order Details " + paymentid);
    }

    private static void sendMail(Object content, String to, String subject) {
        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        String from = "order.anbfh@gmail.com";//change accordingly
        final String username = "order.anbfh";//change accordingly
        final String password = "anbfh@123";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            message.setContent(content, "text/html");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        SendMail sendMail = new SendMail();
        sendMail.execute();
    }

    private static class SendMail extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.e("doInBackground: ", "Start of sending mail");
                Transport.send(message);
                Log.e("doInBackground: ", "End of sending mail");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
