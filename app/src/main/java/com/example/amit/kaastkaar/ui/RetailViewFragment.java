package com.example.amit.kaastkaar.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Context;
import android.database.DataSetObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amit.kaastkaar.ItemsList;
import com.example.amit.kaastkaar.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by amit on 14-10-2016.
 */
public class RetailViewFragment extends Fragment implements RetailDialogFragment.RetailDialogListener {

    public static final String LOG_TAG = RetailViewFragment.class.getSimpleName();
    private FloatingActionButton fab;
    private boolean isConnected;
    private RecyclerView recyclerView;
    private Context mContext;
//    ItemsList itemsList;
    List<ItemsList> listItems = new ArrayList<>();
    private static List<ItemsList> mItemsList = new ArrayList<>();


    ItemsAdapter mAdapter;

    public static RetailViewFragment newInstance() {
        RetailViewFragment retailViewFragment = new RetailViewFragment();
        Bundle bundle = new Bundle();
        retailViewFragment.setArguments(bundle);
        return retailViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        Log.v(LOG_TAG, "Hi from RetailFragment");
        mContext = getActivity();

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_retailview, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        recyclerView =(RecyclerView) view.findViewById(R.id.recycler_view);
/*
        ItemsList list = ItemsList.createItemsList()
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);


        */

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    public void onFinishDialog(List<ItemsList> list) {
 //       Toast.makeText(getActivity(), "Hi, " + inputText, Toast.LENGTH_SHORT).show();
        listItems = list;
        if (listItems.size() != 0) {
            mAdapter = new ItemsAdapter(mContext, listItems);
            int curSize = mAdapter.getItemCount();
            mAdapter.notifyItemRangeChanged(curSize,1);
      
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //     listItems = ItemsList.createItemsList(itemsList.getItemName(),itemsList.getQuantity(),itemsList.getPrice());
   /*
        if (listItems.size() != 0) {
            mAdapter = new ItemsAdapter(mContext, listItems);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(mAdapter);

        }
        */
    }

    private void showDialog() {
        FragmentManager fm = getFragmentManager();
        RetailDialogFragment retailDialogFragment = RetailDialogFragment.newInstance("Post your requirement");
        Log.v(LOG_TAG, "inside dialog");
        // SETS the target fragment for use later when sending results
        retailDialogFragment.setTargetFragment(RetailViewFragment.this, 300);
       retailDialogFragment.show(fm,"dialog");
    }
    public class ItemsAdapter extends RecyclerView.Adapter<ViewHolder> {


        private Context mContext;

        public ItemsAdapter(Context context, List<ItemsList> itemsLists){
            int position =  mItemsList.size();
            mItemsList.add(position, itemsLists.get(0));
            mContext = context;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View itemsView = inflater.inflate(R.layout.recyclerview_item, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(itemsView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            // Get the data model based on position
            if (mItemsList.size() != 0) {
                ItemsList list = mItemsList.get(position);

                // Set item views based on your views and data model
                TextView nameView = viewHolder.nameTextView;
                nameView.setText(list.getItemName());
                viewHolder.quantityTextView.setText(list.getQuantity());
                viewHolder.priceTextView.setText(list.getPrice());

            }
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return mItemsList.size();
        }


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView quantityTextView;
        public TextView priceTextView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
            quantityTextView = (TextView) itemView.findViewById(R.id.item_quantity);
            priceTextView =(TextView) itemView.findViewById(R.id.item_price);
        }
    }


}
