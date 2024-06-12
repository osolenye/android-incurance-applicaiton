package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.models.Person;
import com.example.finalproject.models.UserData;
import com.example.finalproject.network.ApiService;
import com.example.finalproject.network.RetrofitClient;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegTwoFragment extends Fragment {
    private static final String TAG = "reg one fragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegTwoFragment() {
        // Required empty public constructor
    }

    private HashMap<String, String> userData;


    private EditText firstNameEditText, lastNameEditText, placeOfBirthEditText, dateOfBirthEditText, passportNumberEditText, issueDateEditText, expiryDateEditText, accountNumberEditText, registrationAddressEditText, residentialAddressEditText;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegTwoFragment newInstance(String param1, String param2) {
        RegTwoFragment fragment = new RegTwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg_two, container, false);

        if (getArguments() != null) {
            UserData userDataObj = (UserData) getArguments().getSerializable("userData");
            if (userDataObj != null) {
                userData = userDataObj.getUserData();
                // Use the userData map
                Toast.makeText(getContext(), userData.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        // Initialize the EditText fields
        firstNameEditText = view.findViewById(R.id.first_name);
        lastNameEditText = view.findViewById(R.id.last_name);
        placeOfBirthEditText = view.findViewById(R.id.place_of_birth);
        dateOfBirthEditText = view.findViewById(R.id.date_of_birth);
        passportNumberEditText = view.findViewById(R.id.passport_number);
        issueDateEditText = view.findViewById(R.id.issue_date);
        expiryDateEditText = view.findViewById(R.id.expiry_date);
        accountNumberEditText = view.findViewById(R.id.account_number);
        registrationAddressEditText = view.findViewById(R.id.registration_address);
        residentialAddressEditText = view.findViewById(R.id.residential_address);

        // Set up button click listener to read data
        view.findViewById(R.id.btn_reg_two_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataAndSendToServer();
            }
        });

        return view;
    }

    private void readDataAndSendToServer() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String placeOfBirth = placeOfBirthEditText.getText().toString();
        String dateOfBirth = dateOfBirthEditText.getText().toString();
        String passportNumber = passportNumberEditText.getText().toString();
        String issueDate = issueDateEditText.getText().toString();
        String expiryDate = expiryDateEditText.getText().toString();
        String accountNumber = accountNumberEditText.getText().toString();
        String registrationAddress = registrationAddressEditText.getText().toString();
        String residentialAddress = residentialAddressEditText.getText().toString();

        Person person = new Person(firstName, lastName, placeOfBirth, dateOfBirth, passportNumber, issueDate, expiryDate, accountNumber, registrationAddress, residentialAddress, userData.get("email"), userData.get("inn"), userData.get("userName"), userData.get("password1"));

        // Send data to server
        sendPersonToServer(person);
    }

    private void sendPersonToServer(Person person) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.sendPerson(person);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Request successful
                    Toast.makeText(getContext(), "Data sent successfully", Toast.LENGTH_SHORT).show();
                    AuthFragment authFragment = new AuthFragment();
                    if (getActivity() != null) {
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, authFragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d(TAG, "Fragment replaced successfully");
                    } else {
                        Log.e(TAG, "Activity is null");
                    }
                } else {
                    // Request failed
                    try {
                        // Get the error message from the response body
                        String errorBody = response.errorBody().string();
                        Toast.makeText(getContext(), "Failed to send data: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Failed to send data: Error reading response", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Network error or unexpected exception
                Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}