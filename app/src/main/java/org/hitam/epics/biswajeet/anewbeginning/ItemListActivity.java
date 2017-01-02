package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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
            items.add(new Item("Rice Bag", 500, R.drawable.rice));
            items.add(new Item("Dal/kg", 40, R.drawable.dal));
            items.add(new Item("Flour/kg", 30, R.drawable.flour));
            items.add(new Item("Onions/kg", 35, R.drawable.onion));
            items.add(new Item("potato", 35, R.drawable.potato));
        } else if (extras.getInt("donate") == 1) {
            items.add(new Item("Old men", 300, R.drawable.oldmen));
            items.add(new Item("Old Women", 450, R.drawable.sareeold));
            items.add(new Item("kids", 350, R.drawable.kid));
            items.add(new Item("Mens", 459, R.drawable.shirts));
        } else if (extras.getInt("donate") == 2) {
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
