package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.livquik.qwsdkui.QWSDKLibInit;
import com.livquik.qwsdkui.core.QWConstants;
import com.livquik.qwsdkui.model.QWParams;

import org.hitam.epics.biswajeet.anewbeginning.support.CheckoutItem;

public class AddToCartActivity extends Activity {
    ImageView ItemImage;
    TextView ItemName;
    TextView ItemPrice;
    float price;
    EditText quantity;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);

        ItemImage = (ImageView) findViewById(R.id.item_icon);
        ItemName = (TextView) findViewById(R.id.item_name);
        ItemPrice = (TextView) findViewById(R.id.item_price);
        quantity = (EditText) findViewById(R.id.quantity);

        extras = getIntent().getExtras();

        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(extras.getString("image"));
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(reference)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(ItemImage);
        ItemName.setText(extras.getString("name"));
        price = extras.getFloat("price");
        ItemPrice.setText(price + "");
    }


    public void addToCart(View view) {

        if (quantity.getText().toString().trim().length() == 0) {
            quantity.setError("Quantity Required");
            return;
        }

        int qty = Integer.parseInt(String.valueOf(quantity.getText()));
        CheckoutItem checkoutItem = null;
        for (CheckoutItem item : CheckoutActivity.CheckOutList) {
            if (item.getName().equals(extras.getString("name"))) {
                checkoutItem = item;
                break;
            }
        }

        if (checkoutItem != null) {
            int i = CheckoutActivity.CheckOutList.indexOf(checkoutItem);
            checkoutItem.setQuantity(checkoutItem.getQuantity() + qty);
            CheckoutActivity.CheckOutList.remove(i);
            CheckoutActivity.CheckOutList.add(checkoutItem);
        } else {
            checkoutItem = new CheckoutItem(
                    extras.getString("name"), qty,
                    extras.getFloat("price"), extras.getString("image")
            );
            CheckoutActivity.CheckOutList.add(checkoutItem);
        }
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
        finish();
    }
}
