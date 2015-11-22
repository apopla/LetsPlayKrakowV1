package com.example.kitowcy.letsplaykrakow.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;

/**
 * Created by lukasz on 22.11.15.
 */
public class FilterDialogBuilder {
    public interface OnRefreshListener {
        void onRefresh(FilterBuilder currentFilter);
    }
    public static void buildNotification(Context context){
        final Dialog dialog = new Dialog(MainActivity.getInstance());
//        dialog.setTitle("Filter places");

        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        //this makes cardView look
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_validate);
        dialog.findViewById(R.id.OK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public static void build(final Context context, final FilterBuilder currentFilter, final OnRefreshListener listener) {
        Log.d("FilterDialogBuilder", "build ");
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Filter places");

        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        //this makes cardView look
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.filter_dialog);

        final CheckBox food = (CheckBox) dialog.findViewById(R.id.FOOD);
        final CheckBox fun = (CheckBox) dialog.findViewById(R.id.entertainment);
        final CheckBox monument = (CheckBox) dialog.findViewById(R.id.monuments);
        final CheckBox culture = (CheckBox) dialog.findViewById(R.id.culture);

        food.setChecked(currentFilter.contains(FilterBuilder.FOOD));
        fun.setChecked(currentFilter.contains(FilterBuilder.ENTERTAINMENT));
        culture.setChecked(currentFilter.contains(FilterBuilder.CULTURE));
        monument.setChecked(currentFilter.contains(FilterBuilder.MONUMENTS));

        final TextView applyChangesButton = (TextView) dialog.findViewById(R.id.apply);
        applyChangesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                applyChangesButton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                return false;
            }
        });
        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFilter.clear();
                if (food.isChecked())
                    currentFilter.with(FilterBuilder.FOOD);
                if (fun.isChecked())
                    currentFilter.with(FilterBuilder.ENTERTAINMENT);
                if (monument.isChecked())
                    currentFilter.with(FilterBuilder.MONUMENTS);
                if (culture.isChecked())
                    currentFilter.with(FilterBuilder.CULTURE);
                listener.onRefresh(currentFilter);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
