package com.branham.roger.routerecord.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.repositories.FirebaseDbUtils;

import java.util.Calendar;

public class AddTripFragment extends Fragment implements DatePickerDialog.OnDateSetListener  {

    String TAG = "addTripFragment";

    private String currentDateString;

    //Views
    private View mView; //Could be changed to just storing the views context
    private Spinner mSpinner;
    private EditText mDateTextField, mNameTextField, mBusTextField, mHoursTextField, mMilesTextField;
    private Button mSubmitButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Create Fragment View
        mView = inflater.inflate(R.layout.fragment_add_trip,container,false);

        //Grab views in fragment
        mSpinner = (Spinner) mView.findViewById(R.id.input_spinner_type);
        mSubmitButton = (Button) mView.findViewById(R.id.button_submit);
        mDateTextField = (EditText) mView.findViewById(R.id.input_date);
        mNameTextField = (EditText) mView.findViewById(R.id.input_trip_name);
        mBusTextField = (EditText) mView.findViewById(R.id.input_bus);
        mHoursTextField = (EditText) mView.findViewById(R.id.input_hours);
        mMilesTextField = (EditText) mView.findViewById(R.id.input_miles);

        //Populate Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mView.getContext(),
                R.array.trip_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinner.setAdapter(adapter);

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

        //Return fragment view
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

    /**function to pull data from all of the input fields*/
    private void pullDataFromFields(){
        Trip trip = new Trip();

        //Pull Name field
        String name = mNameTextField.getText().toString().trim();
        if(name.length() > 0){
            trip.setTripName(name);
        } else {
            Log.w(TAG, "Trip input field empty");
            Toast.makeText(mView.getContext(), "Name text field must be filled out", Toast.LENGTH_SHORT).show();
            return;
        }

        //Pull Hours
        if(mHoursTextField.getText() != null) { //TODO add error
            float hours = Float.parseFloat(mHoursTextField.getText().toString().trim());
            if (hours > 0) {
                trip.setHours(hours);
            } else {
                Log.w(TAG, "Hours input field not greater than 0");
                Toast.makeText(mView.getContext(), "Hours must be a decimal number greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //Pull Miles
        float miles = Float.parseFloat(mMilesTextField.getText().toString().trim());
        if(miles > 0){
            trip.setMiles(miles);
        } else {
            Log.w(TAG, "Miles input field not greater than 0");
            Toast.makeText(mView.getContext(), "Miles must be a decimal number greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        //pull Spinner Value
        String tripType = mSpinner.getItemAtPosition(mSpinner.getSelectedItemPosition()).toString();
        trip.setJobType(tripType);

        //Pull Bus Number
        int busNum = Integer.parseInt(mBusTextField.getText().toString().trim());
        if(busNum > 0){
            trip.setBusNum(busNum);
        } else {
            Log.w(TAG, "Bus Number input field not greater than 0");
            Toast.makeText(mView.getContext(), "Bus number must be an integer greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        //push date
        trip.setDate(currentDateString);

        //Insert into Database
        FirebaseDbUtils.addTrip(trip); //TODO: Get confirmation?

        //clear input fields
        clearFields();

        Toast.makeText(mView.getContext(), "Trip added", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {

        mDateTextField.getText().clear();
        mNameTextField.getText().clear();
        mBusTextField.getText().clear();
        mHoursTextField.getText().clear();
        mMilesTextField.getText().clear();

    }
}
