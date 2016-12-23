package org.hitam.epics.biswajeet.anewbeginning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by biswajeet on 23/12/16.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newView = inflater.inflate(R.layout.item, parent);
        }

        Item currentItem = getItem(position);

        ImageView itemIcon = (ImageView) newView.findViewById(R.id.item_icon);
//        Todo: to be implemented

        TextView itemName = (TextView) newView.findViewById(R.id.item_name);
        itemName.setText(currentItem.getName());

        TextView itemPrice = (TextView) newView.findViewById(R.id.item_price);
        itemPrice.setText(currentItem.getPrice() + "");

        return newView;
    }
}
