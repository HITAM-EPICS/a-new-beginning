package org.hitam.epics.biswajeet.anewbeginning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.hitam.epics.biswajeet.anewbeginning.support.Item;
import org.hitam.epics.biswajeet.anewbeginning.support.ItemAdapter;

import java.util.ArrayList;

public class ItemListActivity extends Activity {
    private GridView itemListGridView;
    private ItemAdapter itemAdapter;
    private ArrayList<Item> items;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        database = FirebaseDatabase.getInstance();

        itemListGridView = (GridView) findViewById(R.id.item_list);
        items = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        if (extras.getInt("donate") == 0) {
            reference = database.getReference("donation_items/grocery/");
            getActionBar().setTitle("Groceries");
        }
        else if(extras.getInt("donate")==1){
            reference=database.getReference("donation_items/cleaningProducts");
            getActionBar().setTitle("Cleaning Products");
        }
        else if(extras.getInt("donate")==2){
            reference=database.getReference("donation_items/toiletries");
            getActionBar().setTitle("Toiletries");
        }

        else {
            finish();
        }

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item item = dataSnapshot.getValue(Item.class);
                items.add(item);
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addChildEventListener(childEventListener);

        itemAdapter = new ItemAdapter(this, items);

        itemListGridView.setAdapter(itemAdapter);

        itemAdapter.notifyDataSetChanged();

        itemListGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = items.get(i);
                Bundle extras = new Bundle();
                extras.putString("image", item.getPictureurl());
                extras.putString("name", item.getName());
                extras.putFloat("price", item.getPrice());
                Intent intent = new Intent(ItemListActivity.this, AddToCartActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        itemListGridView.setEmptyView(findViewById(R.id.empty_view));
    }
}
