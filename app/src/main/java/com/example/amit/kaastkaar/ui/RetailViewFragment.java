package com.example.amit.kaastkaar.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Context;
import android.database.DataSetObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.amit.kaastkaar.R;

/**
 * Created by amit on 14-10-2016.
 */
public class RetailViewFragment extends Fragment implements RetailDialogFragment.RetailDialogListener {

    public static final String LOG_TAG = RetailViewFragment.class.getSimpleName();
    private FloatingActionButton fab;
    private boolean isConnected;
    private RecyclerView recyclerView;
    private Context mContext;

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

        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected){

                    showDialog();

                }

            }
        });
    }

    @Override
    public void onFinishDialog(String inputText) {
        Toast.makeText(getActivity(), "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }

    private void showDialog() {
        FragmentManager fm = getFragmentManager();
        RetailDialogFragment retailDialogFragment = RetailDialogFragment.newInstance("Post your requirement");
        Log.v(LOG_TAG, "inside dialog");
        // SETS the target fragment for use later when sending results
        retailDialogFragment.setTargetFragment(RetailViewFragment.this, 300);
       retailDialogFragment.show(fm,"dialog");
    }


}
