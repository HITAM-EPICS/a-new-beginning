package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DonateActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);


    }

    public void donateFood(View view) {
        Toast.makeText(DonateActivity.this, "Groceries", Toast.LENGTH_SHORT).show();
        Bundle extras = new Bundle();
        extras.putInt("donate", 0);
        Intent intent = new Intent(DonateActivity.this, ItemListActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void donateClothes(View view) {
        Toast.makeText(DonateActivity.this, "Cleaning Products", Toast.LENGTH_SHORT).show();
        Bundle extras = new Bundle();
        extras.putInt("donate", 1);
        Intent intent = new Intent(DonateActivity.this, ItemListActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void donateGrocery(View view) {
        Toast.makeText(DonateActivity.this, "Toiletries", Toast.LENGTH_SHORT).show();
        Bundle extras = new Bundle();
        extras.putInt("donate", 2);
        Intent intent = new Intent(DonateActivity.this, ItemListActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void ShowCart(View view) {
        startActivity(new Intent(this,CheckoutActivity.class));
    }
}
