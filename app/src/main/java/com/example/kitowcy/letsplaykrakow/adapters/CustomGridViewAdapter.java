package com.example.kitowcy.letsplaykrakow.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitowcy.letsplaykrakow.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author manish.s
 */
public class CustomGridViewAdapter extends ArrayAdapter<Pair<Integer, String>> {
    Context context;
    int layoutResourceId;
    List<Pair<Integer, String>> data = new ArrayList<>();

    public CustomGridViewAdapter(Context context, @LayoutRes int layoutResourceId,
                                 @NonNull List<Pair<Integer, String>> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Pair<Integer, String> item = data.get(position);
        holder.txtTitle.setText(item.second);
        Picasso.with(context).load(item.first).into(holder.imageItem);
        return row;
    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
    }
}
