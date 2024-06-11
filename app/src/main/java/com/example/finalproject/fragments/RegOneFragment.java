package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.models.UserData;
import com.example.finalproject.models.YurUser;
import com.example.finalproject.network.ApiService;
import com.example.finalproject.network.RetrofitClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegOneFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegOneFragment() {
        // Required empty public constructor
    }
    private HashMap<String, String> userData;
    private EditText organizationName, okpoCode, registrationNumber, leaderName, leaderPosition, representativeName, legalAddress, actualAddress;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegOneFragment newInstance(String param1, String param2) {
        RegOneFragment fragment = new RegOneFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg_one, container, false);
        if (getArguments() != null) {
            UserData userDataObj = (UserData) getArguments().getSerializable("userData");
            if (userDataObj != null) {
                userData = userDataObj.getUserData();
                // Use the userData map
                Toast.makeText(getContext(), userData.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        view.findViewById(R.id.btn_reg_one_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize the EditText fields
                organizationName = view.findViewById(R.id.organization_name);
                okpoCode = view.findViewById(R.id.okpo_code);
                registrationNumber = view.findViewById(R.id.registration_number);
                leaderName = view.findViewById(R.id.leader_name);
                leaderPosition = view.findViewById(R.id.leader_position);
                representativeName = view.findViewById(R.id.representative_name);
                legalAddress = view.findViewById(R.id.legal_address);
                actualAddress = view.findViewById(R.id.actual_address);


                String orgName = organizationName.getText().toString();
                String okpo = okpoCode.getText().toString();
                String regNum = registrationNumber.getText().toString();
                String leadName = leaderName.getText().toString();
                String leadPos = leaderPosition.getText().toString();
                String repName = representativeName.getText().toString();
                String legalAddr = legalAddress.getText().toString();
                String actualAddr = actualAddress.getText().toString();


                YurUser yurUser = new YurUser(
                        orgName,
                        okpo,
                        regNum,
                        leadName,
                        leadPos,
                        repName,
                        legalAddr,
                        actualAddr,
                        userData.get("email"),
                        userData.get("inn"),
                        userData.get("userName"),
                        userData.get("password1"),
                        userData.get("accountNumber")
                );
                sendYurUserToServer(yurUser);
            }
        });



        return view;
    }


    private void sendYurUserToServer(YurUser yurUser) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.sendYurUser(yurUser);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Request successful
                    Toast.makeText(getContext(), "Data sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Request failed
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Network error
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}