package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ItemListActivity extends Activity {
    private GridView itemListGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        itemListGridView = (GridView) findViewById(R.id.item_list);

        final ArrayList<Item> items = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        if (extras.getInt("donate") == 0) {
            items.add(new Item("VEG Curry(all items)", 45, R.drawable.vegcurry));
            items.add(new Item("Non VEG Curry(all items)", 100, R.drawable.nonveg));
            items.add(new Item("Roti + Veg items",60 , R.drawable.rotiveg));
            items.add(new Item("Roti+ Non Veg Items", 80, R.drawable.rotinveg));
            items.add(new Item("Veg Biryani",100, R.drawable.vegbiryank));
            items.add(new Item("Chicken Biryani",120, R.drawable.chickenbir));
        } else if (extras.getInt("donate") == 1) {
            items.add(new Item("Old men", 300, R.drawable.oldmen));
            items.add(new Item("Old Women", 450, R.drawable.sareeold));
            items.add(new Item("kids", 350, R.drawable.kid));
            items.add(new Item("Mens", 459, R.drawable.shirts));
        } else if (extras.getInt("donate") == 2) {
            items.add(new Item("Rice Bag", 500, R.drawable.rice1));
            items.add(new Item("Dal/kg", 40, R.drawable.dal1));
            items.add(new Item("Flour/kg", 30, R.drawable.flou1));
            items.add(new Item("Onions/kg", 35, R.drawable.onions1));
            items.add(new Item("potato", 35, R.drawable.potatoes));
            items.add(new Item("Coconut Oil", 57, R.drawable.cocunut));
            items.add(new Item("Soaps", 59, R.drawable.soap));
            items.add(new Item("Amruntanjan", 32, R.drawable.amrut));

        }

        ItemAdapter itemAdapter = new ItemAdapter(this, items);

        itemListGridView.setAdapter(itemAdapter);

        itemAdapter.notifyDataSetChanged();

        itemListGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = items.get(i);
                Bundle extras = new Bundle();
                extras.putInt("image", item.getPictureURL());
                extras.putString("name", item.getName());
                extras.putFloat("price", item.getPrice());
                Intent intent = new Intent(ItemListActivity.this, CheckoutActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
