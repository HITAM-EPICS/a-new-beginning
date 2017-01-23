package org.hitam.epics.biswajeet.anewbeginning.support;

import org.hitam.epics.biswajeet.anewbeginning.CheckoutActivity;

/**
 * Created by biswajeet on 23/1/17.
 */

public class Mailing {
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
            "<div style=\"float:left;margin:2px 0 0 26px;font-size:22px;font-weight:100;line-height:29px\"> Your <span style=\"font-weight:400\">Donation Order</span> for <br/><span style=\"color:#00b0de;font-weight:600\">A New Beggining for homeless</span>" +
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

    public static void makePaymentBill() {
        Bill = "";
        for (CheckoutItem item :
                CheckoutActivity.CheckOutList) {

        }
    }

    public static void sendPaymentIntialisingMail() {

    }

    public static void sendPaymentConfirmationMail(String paymentid) {

    }
}
