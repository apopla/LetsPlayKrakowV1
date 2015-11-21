package com.example.kitowcy.letsplaykrakow;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Lukasz Marczak on 2015-11-17.
 */
public class MaterialDrawerAdapter extends RecyclerView.Adapter<MaterialDrawerAdapter.ViewHolder> {


    public void setItemSelected(String itemSelected) {
        if (itemSelected.equals("OFFERS")) {
            refreshSelection(1);
        } else if (itemSelected.equals("JOURNEYS")) {
            refreshSelection(0);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public static MaterialDrawerAdapter instance;

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    private static final int TYPE_ITEM = 1;    // IF the view under inflation and population is header or Item

    private AppCompatActivity context;
    private List<String> navigationTitles; // String Array to store the passed titles Value from mejnActivity
    private String name;        //String Resource for header View Name
    private String profileImageUrl;      //int Resource for header view profileImageUrl picture
    private String email;       //String Resource for header view email
    protected boolean userIsLogged;
    private int selectedPos = -1;
    private int currentPartnersCount = 0;
    private int currentOffersCount = 0;

    public void setOnClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @Nullable
    private OnItemClickListener clickListener;

    public void updateBasketInterior(int partners, int offers) {
        currentPartnersCount = partners;
        currentOffersCount = offers;
        notifyDataSetChanged();
    }

    private void refreshSelection(int position) {
        selectedPos = position;
        notifyDataSetChanged();
    }

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;
        RelativeLayout parent;
        ImageView profile;
        //        TextView Name;
        TextView email;

        /**
         * header:user not logged
         */
        RelativeLayout headerParent;
        Button signInButton;
        RelativeLayout myShoppingListViewGroup;
        /**
         * header:user logged in
         */
        LinearLayout profileLayout;
        CircleImageView circleView;
        TextView checkOffersLabel;
        TextView offersCount;
        TextView partnersCount;
        ImageView basketIcon;

        /**
         * @param itemView
         * @param ViewType
         * @param isUserLogged
         */
        public ViewHolder(View itemView, int ViewType, boolean isUserLogged) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
//                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                parent = (RelativeLayout) itemView.findViewById(R.id.parent);
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            } else {

                if (isUserLogged) {
                    circleView = (CircleImageView) itemView.findViewById(R.id.circleView);
                    profileLayout = (LinearLayout) itemView.findViewById(R.id.profile_layout);
                    checkOffersLabel = (TextView) itemView.findViewById(R.id.textview_check_your_offers);
                    basketIcon = (ImageView) itemView.findViewById(R.id.basket_button_image);
                    offersCount = (TextView) itemView.findViewById(R.id.offers_basket_count);
                    partnersCount = (TextView) itemView.findViewById(R.id.partners_basket_count);

                } else {
                    myShoppingListViewGroup = (RelativeLayout) itemView.findViewById(R.id.shopping_list_viewgroup);
                    signInButton = (Button) itemView.findViewById(R.id.sign_in_button);
                }
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }
    }

    public MaterialDrawerAdapter(AppCompatActivity context, List<String> Titles, String Name, String Email, String Profile, boolean isUserLogged) { // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profileImageUrl pic are passed from the main activity as we
        navigationTitles = Titles;                //have seen earlier
        name = Name;
        email = Email;
        profileImageUrl = Profile;                     //here we assign those passed values to the values we declared here
        this.context = context;
        this.userIsLogged = isUserLogged;
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
            ViewHolder vhItem = new ViewHolder(v, viewType, userIsLogged); //Creating ViewHolder and passing the object of type view
            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {
            int layout = userIsLogged ? R.layout.activity_main_material_header_user_logged :
                    R.layout.activity_main_material_header_user_not_logged;
            View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v, viewType, userIsLogged); //Creating ViewHolder and passing the object of type view
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

            if (selectedPos == position) {
                holder.textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                holder.textView.setTextColor(context.getResources().getColor(android.R.color.black));
            }
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshSelection(holder.getAdapterPosition());
                    if (clickListener != null)
                        clickListener.onItemClicked(position - 1);
                }
            });
        } else {
            if (userIsLogged) {

                if (!profileImageUrl.equals("")) {
                    Picasso.with(context).load(profileImageUrl).into(holder.circleView);
                } else {
                    holder.circleView.setImageResource(R.drawable.avatar);
                }

                holder.partnersCount.setText(String.valueOf(currentPartnersCount));
                holder.offersCount.setText(String.valueOf(currentOffersCount));
                holder.profileLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentSwitcher.switchToFragment(context, FragmentUnit.SETTINGS, R.id.activity_main_fragment_placeholder, null);
                        refreshSelection(-1);
                        if (clickListener != null)
                            clickListener.onItemClicked(-1);
                    }
                });
                holder.checkOffersLabel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentSwitcher.switchToFragment(context, FragmentUnit.START, R.id.activity_main_fragment_placeholder, null);
                        refreshSelection(-1);
                        if (clickListener != null)
                            clickListener.onItemClicked(-1);

                    }
                });
                holder.basketIcon.setOnClickListener(switchToBasketActivity());
            } else { //user not logged
                holder.signInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(context, LoginActivity.class);
                        context.startActivity(i);
                        refreshSelection(-1);
                        if (clickListener != null)
                            clickListener.onItemClicked(-1);
                        context.finish();
                    }
                });
                holder.myShoppingListViewGroup.setOnClickListener(switchToBasketActivity());
            }
        }
    }

    private View.OnClickListener switchToBasketActivity() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshSelection(-1);
                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);
                if (clickListener != null)
                    clickListener.onItemClicked(-1);
            }
        };
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