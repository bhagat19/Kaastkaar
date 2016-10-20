package com.example.amit.kaastkaar.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amit.kaastkaar.ItemsList;
import com.example.amit.kaastkaar.R;

import java.util.List;

/**
 * Created by amit on 17-10-2016.
 */
public class RetailDialogFragment extends DialogFragment {

    private AutoCompleteTextView autoCompleteTextView;
    private EditText quantity;
    private EditText price;
    private Button post;

    public RetailDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static RetailDialogFragment newInstance(String title) {
       RetailDialogFragment frag = new RetailDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.retailer_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get field from view
       autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.item_picker);
        quantity = (EditText) view.findViewById(R.id.quantity);
        price = (EditText) view.findViewById(R.id.price);
        post = (Button) view.findViewById(R.id.action_done);

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Post your Requirement");
        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
    //    quantity.requestFocus();
     //   price.requestFocus();
    //    getDialog().getWindow().setSoftInputMode(
      //          WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ItemsList> list = ItemsList.createItemsList(autoCompleteTextView.getText().toString(),
                        quantity.getText().toString(),price.getText().toString());
       //         Toast.makeText(getActivity(),price.getText().toString(),Toast.LENGTH_SHORT ).show();

                sendBackResult(list);
            }
        });

    }

    public interface RetailDialogListener {
        void onFinishDialog(List<ItemsList> inputList);
    }

    // Call this method to send the data back to the parent fragment
    public void sendBackResult(List<ItemsList> list) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        RetailDialogListener listener = (RetailDialogListener) getTargetFragment();
        listener.onFinishDialog(list);
        dismiss();
    }


}
