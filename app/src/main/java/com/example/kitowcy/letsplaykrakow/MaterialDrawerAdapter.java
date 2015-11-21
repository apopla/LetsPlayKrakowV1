package com.example.kitowcy.letsplaykrakow;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


public class MaterialDrawerAdapter extends RecyclerView.Adapter<MaterialDrawerAdapter.ViewHolder> {

    public static MaterialDrawerAdapter instance;

    public interface onItemClickListener{
        void onClick(int position);
    }
    private onItemClickListener listener;
    public void setOnClickListener(onItemClickListener h){
        listener=h;
    }
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    private static final int TYPE_ITEM = 1;    // IF the view under inflation and population is header or Item

    private AppCompatActivity context;
    private List<String> navigationTitles; // String Array to store the passed titles Value from mejnActivity

    private int selectedPos = -1;

    private void refreshSelection(int position) {
        selectedPos = position;
        notifyDataSetChanged();
    }

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        RelativeLayout parent;
        TextView textView;
        ImageView menuImage;

        /**
         * @param itemView
         * @param ViewType
         */
        public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                menuImage = (ImageView) itemView.findViewById(R.id.image_menu_icon);// Creating ImageView object with the id of ImageView from item_row.xml
                parent = (RelativeLayout) itemView.findViewById(R.id.parent);
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            } else {
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }
    }

    public MaterialDrawerAdapter(AppCompatActivity context, List<String> Titles) { // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profileImageUrl pic are passed from the main activity as we
        navigationTitles = Titles;                //have seen earlier
        this.context = context;
        instance = this;
        //in adapter
    }


    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_material_item_row, parent, false); //Inflating the layout
            ViewHolder vhItem = new ViewHolder(v, viewType); //Creating ViewHolder and passing the object of type view
            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_material_header, parent, false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v, viewType); //Creating ViewHolder and passing the object of type view
            return vhHeader; //returning the object created
        }
        return null;
    }


    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder.Holderid == 1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(navigationTitles.get(position - 1)); // Setting the Text with the array of our Titles
//            holder.imageView.setImageResource(mIcons[position - 1]);// Settimg the image with array of our icons
            int image;
            switch (position - 1){
                case 0:
                    image = R.drawable.menu_map;
                    break;
                case 1:
                    image = R.drawable.menu_routes;
                    break;
                case 2:
                    image = R.drawable.menu_places;
                    break;
                default:
                    image = R.drawable.menu_achievement;
                    break;
            }
            holder.menuImage.setImageResource(image);
            if (selectedPos == position) {
                holder.textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                holder.textView.setTextColor(context.getResources().getColor(android.R.color.black));
            }
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshSelection(holder.getAdapterPosition());
                    if (listener != null)
                        listener.onClick(position - 1);
                }
            });
        } else { //user not logged

        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return navigationTitles.size() + 1; // the number of items in the list will be +1 the titles including the header view.
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) // is Header?
            return TYPE_HEADER;
        return TYPE_ITEM;
    }
}