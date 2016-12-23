package org.hitam.epics.biswajeet.anewbeginning;

import android.content.Intent;
import android.gesture.GestureUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestActivity extends AppCompatActivity {
    ListView menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        menu=(ListView)findViewById(R.id.guest_menu);
        ArrayList menuList = new ArrayList();
        menuList.add("Donate FOOD");
        menuList.add("Donate CLOTHES");
        menuList.add("Donate GROCERIES");
        ArrayAdapter menuAdapter=new ArrayAdapter(this,R.layout.menu_item,menuList);
        menu.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(GuestActivity.this,"Food",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(GuestActivity.this,ItemListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(GuestActivity.this,"Clothes",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(GuestActivity.this,"Groceries",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}
