package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends Activity {
    ImageView ItemImage;
    TextView ItemName;
    TextView ItemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        ItemImage = (ImageView) findViewById(R.id.item_icon);
        ItemName = (TextView) findViewById(R.id.item_name);
        ItemPrice = (TextView) findViewById(R.id.item_price);

        Bundle extras = getIntent().getExtras();

        ItemImage.setImageResource(extras.getInt("image"));
        ItemName.setText(extras.getString("name"));
        ItemPrice.setText(extras.getFloat("price") + "");
    }

    public void checkout(View view) {
//        todo: implement checkout
        finish();
    }
}
