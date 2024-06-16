package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.models.AccidentPolicy;
import com.example.finalproject.models.Policy;
import com.example.finalproject.network.ApiService;
import com.example.finalproject.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccidentInsuranceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccidentInsuranceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccidentInsuranceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccidentInsuranceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccidentInsuranceFragment newInstance(String param1, String param2) {
        AccidentInsuranceFragment fragment = new AccidentInsuranceFragment();
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
        View view = inflater.inflate(R.layout.fragment_accident_insurance, container, false);

        view.findViewById(R.id.btn_create_accident_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_start_date = view.findViewById(R.id.accident_date_begin);
                EditText et_end_date = view.findViewById(R.id.accident_date_end);
                EditText et_travel_country = view.findViewById(R.id.accident_travel_country);

                String start_date = et_start_date.getText().toString();
                String end_date = et_end_date.getText().toString();
                String travel_country = et_travel_country.getText().toString();

                AccidentPolicy accidentPolicy = new AccidentPolicy(start_date, end_date, travel_country);
                sendAccidentPolicy(accidentPolicy);
            }
        });

        return view;
    }


    private void sendAccidentPolicy(AccidentPolicy accidentPolicy) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.accidentPolicies("Bearer " + AuthFragment.accessToken, accidentPolicy);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Request successful
                    Toast.makeText(getContext(), "Data sent successfully", Toast.LENGTH_SHORT).show();
//                    AuthFragment authFragment = new AuthFragment();
//                    if (getActivity() != null) {
//                        requireActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.container, authFragment)
//                                .addToBackStack(null)
//                                .commit();
//                        Log.d(TAG, "Fragment replaced successfully");
//                    } else {
//                        Log.e(TAG, "Activity is null");
//                    }
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