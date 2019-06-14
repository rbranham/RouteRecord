package com.branham.roger.routerecord.ui;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.repositories.FirebaseDbUtils;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsertFutureTripFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "InsertFutureTripFrag";

    private String currentDateString;

    //Views
    private View mView; //Could be changed to just storing the views context
    private EditText mDateTextField, mNameTextField;
    private Button mSubmitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_insert_future_trip, container, false);

        mDateTextField = (EditText) mView.findViewById(R.id.input_date);
        mNameTextField = (EditText) mView.findViewById(R.id.input_trip_name);
        mSubmitButton = (Button) mView.findViewById(R.id.button_submit);

        //Set onClickListener for Date entry
        mDateTextField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                showDatePickerDialog();
            }
        });

        //Set onClickListener for Submit Button
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pullDataFromFields();
            }
        });

        return mView;
    }

    /** Function Shows the Date Picker dialog*/
    private void showDatePickerDialog(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mView.getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    /**Function Sets saves the selected date from user */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        currentDateString = month + "/" + day + "/" + year;
        mDateTextField.setText(currentDateString);
    }

    private void pullDataFromFields() {

        Trip trip = new Trip();

        //Set Booleans
        trip.setCompleted(false);
        trip.setAvailable(true);

        //Pull Name field
        String name = mNameTextField.getText().toString().trim();
        if(name.length() > 0){
            trip.setTripName(name);
        } else {
            Log.w(TAG, "Trip input field empty");
            Toast.makeText(mView.getContext(), "Name text field must be filled out", Toast.LENGTH_SHORT).show();
            return;
        }


        //push date
        trip.setDate(currentDateString);

        //Insert into Database
        FirebaseDbUtils.addAvailibleTrip(trip); //TODO: Get confirmation?

        //clear input fields
        clearFields();

        Toast.makeText(mView.getContext(), "Trip added", Toast.LENGTH_SHORT).show();

    }

    /**Clears Input Fields*/
    private void clearFields() {
        mDateTextField.getText().clear();
        mNameTextField.getText().clear();
    }

}
