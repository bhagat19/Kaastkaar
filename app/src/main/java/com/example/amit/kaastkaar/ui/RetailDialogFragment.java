package com.example.amit.kaastkaar.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.amit.kaastkaar.R;

/**
 * Created by amit on 17-10-2016.
 */
public class RetailDialogFragment extends DialogFragment {

    private AutoCompleteTextView autoCompleteTextView;
    private EditText quantity;
    private EditText price;

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
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Post your Requirement");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
    //    quantity.requestFocus();
     //   price.requestFocus();
    //    getDialog().getWindow().setSoftInputMode(
      //          WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        sendBackResult();
    }

    public interface RetailDialogListener {
        void onFinishDialog(String inputText);
    }

    // Call this method to send the data back to the parent fragment
    public void sendBackResult() {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        RetailDialogListener listener = (RetailDialogListener) getTargetFragment();
        listener.onFinishDialog("Click add button to post further");
      //  dismiss();
    }


}
