package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
    }

    public void donateFood(View view) {
        Toast.makeText(GuestActivity.this, "Food", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(GuestActivity.this, ItemListActivity.class);
        startActivity(intent);
    }

    public void donateClothes(View view) {
        Toast.makeText(GuestActivity.this, "Clothes", Toast.LENGTH_SHORT).show();
    }

    public void donateGrocery(View view) {
        Toast.makeText(GuestActivity.this, "Groceries", Toast.LENGTH_SHORT).show();
    }
}
