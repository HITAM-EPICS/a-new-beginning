package org.hitam.epics.biswajeet.anewbeginning.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.hitam.epics.biswajeet.anewbeginning.R;

import java.util.ArrayList;

/**
 * Created by biswajeet on 23/12/16.
 */
public class CheckoutItemAdapter extends ArrayAdapter<CheckoutItem> {
    public CheckoutItemAdapter(Context context, ArrayList<CheckoutItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newView = inflater.inflate(R.layout.checkoutitem, null);
        }

        CheckoutItem currentItem = getItem(position);

        ImageView itemIcon = (ImageView) newView.findViewById(R.id.item_icon);
        if (currentItem.getPictureurl() != null) {
            StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(currentItem.getPictureurl());
            Glide.with(getContext())
                    .using(new FirebaseImageLoader())
                    .load(reference)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(itemIcon);
        }

        TextView itemName = (TextView) newView.findViewById(R.id.item_name);
        itemName.setText(currentItem.getName());

        TextView Quantity = (TextView) newView.findViewById(R.id.quantity);
        Quantity.setText(currentItem.getQuantity()+"");


        TextView itemPrice = (TextView) newView.findViewById(R.id.item_price);
        itemPrice.setText(currentItem.getPrice()*currentItem.getQuantity() + "");

        return newView;
    }
}
