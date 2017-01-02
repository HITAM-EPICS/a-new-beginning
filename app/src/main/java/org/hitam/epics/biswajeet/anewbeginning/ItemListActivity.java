package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class ItemListActivity extends Activity {
    private GridView itemListGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        itemListGridView = (GridView) findViewById(R.id.item_list);

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("Rice Bag", 500));
        items.add(new Item("Dal/kg", 40));
        items.add(new Item("Flour/kg", 30));
        items.add(new Item("Onions/kg", 35));
        items.add(new Item("potato", 35));

        ItemAdapter itemAdapter=new ItemAdapter(this,items);

        itemListGridView.setAdapter(itemAdapter);

        itemAdapter.notifyDataSetChanged();
    }
}
