package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.livquik.qwsdkui.QWSDKLibInit;
import com.livquik.qwsdkui.core.QWConstants;
import com.livquik.qwsdkui.model.QWParams;

import org.hitam.epics.biswajeet.anewbeginning.support.CheckoutItem;
import org.hitam.epics.biswajeet.anewbeginning.support.CheckoutItemAdapter;
import org.hitam.epics.biswajeet.anewbeginning.support.Item;

import java.util.ArrayList;

import static org.hitam.epics.biswajeet.anewbeginning.support.Mailing.makePaymentBill;
import static org.hitam.epics.biswajeet.anewbeginning.support.Mailing.sendPaymentConfirmationMail;
import static org.hitam.epics.biswajeet.anewbeginning.support.Mailing.sendPaymentIntialisingMail;

public class CheckoutActivity extends Activity {
    public static ArrayList<CheckoutItem> CheckOutList = new ArrayList<>();
    private CheckoutItemAdapter checkoutItemAdapter;
    ListView CheckOutListView;
    TextView TotalPrice;


    private static final String TAG = "Payment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        checkoutItemAdapter = new CheckoutItemAdapter(this, CheckOutList);
        CheckOutListView = (ListView) findViewById(R.id.Checkout);
        CheckOutListView.setAdapter(checkoutItemAdapter);
        CheckOutListView.setEmptyView(findViewById(R.id.checkout_empty));

        TotalPrice = (TextView) findViewById(R.id.checkout_total);
        TotalPrice.setText("" + calculateTotal());

    }

    public double calculateTotal() {
        double total = 0;
        for (CheckoutItem checkoutItem : CheckOutList) {
            total += checkoutItem.getQuantity() * checkoutItem.getPrice();
        }
        return total;
    }


    public void checkout(View view) {
        if (calculateTotal() > 0) {
            makePaymentBill();
            sendPaymentIntialisingMail();
            QWParams qwParams = new QWParams();//object creation
            qwParams.setMobile("8801485611");
            qwParams.setSignature("6b76594e04bd5e55fa16974559e542963bfa0b8e11a1ff597aee7314ee070819");
            qwParams.setPartnerid("216");
            qwParams.setAmount(String.valueOf(calculateTotal()));
            //For test environment
            qwParams.setEnv(QWConstants.ENV_LIVE); //Use QWConstants.ENV_UAT for testing.
            QWSDKLibInit.init(this, qwParams, QWConstants.PAYMENT);
        } else {
            Toast.makeText(this, "Please add items to cart to checkout", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == QWConstants.QWSDKID) {
            if (data != null) {
                Log.d(TAG, data.getStringExtra(QWConstants.RESPONSE_DESC) + "");
                if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, data.getStringExtra(QWConstants.RESPONSE_DESC) + "", Toast.LENGTH_SHORT).show();
                } else if (resultCode == QWConstants.PAYMENT_SUCCESS_CODE) {
                    String paymentid = data.getStringExtra(QWConstants.PAYMENTID);
                    String msg = data.getStringExtra(QWConstants.RESPONSE_DESC);
                    Log.d(TAG, "paymentid: " + paymentid + " msg :" + msg);
                    Toast.makeText(this, "paymentid " + paymentid + " msg " + msg + "", Toast.LENGTH_SHORT).show();
                    CheckOutList.clear();
                    sendPaymentConfirmationMail(paymentid);
                } else if (resultCode == QWConstants.PAYMENT_FAILED_CODE) {
                    String paymentid = data.getStringExtra(QWConstants.PAYMENTID);
                    String msg = data.getStringExtra(QWConstants.RESPONSE_DESC);
                    Log.d(TAG, "paymentid: " + paymentid + " msg :" + msg);
                    Toast.makeText(this, "paymentid " + paymentid + " msg " + msg + "", Toast.LENGTH_SHORT).show();
                } else if (resultCode == QWConstants.NO_ACTION_PROVIDED_CODE) {
                    Log.d(TAG, "" + data.getStringExtra(QWConstants.RESPONSE_DESC) + "");
                    Toast.makeText(this, data.getStringExtra(QWConstants.RESPONSE_DESC) + "", Toast.LENGTH_SHORT).show();
                } else if (resultCode == QWConstants.INPUT_VALIDATION_FAILED) {
                    Log.d(TAG, data.getStringExtra(QWConstants.RESPONSE_DESC) + "");
                    Toast.makeText(this, data.getStringExtra(QWConstants.RESPONSE_DESC) + "", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
