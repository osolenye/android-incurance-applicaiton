package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PolicyBuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PolicyBuyFragment extends Fragment {
    private static final String TAG = "policy buy fragment";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PolicyBuyFragment() {
        // Required empty public constructor
    }

    AppCompatButton btnAccident, btnAutoInsurance, btnMedInsurance, btnCargoInsurance, btnVzr;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PolicyBuyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PolicyBuyFragment newInstance(String param1, String param2) {
        PolicyBuyFragment fragment = new PolicyBuyFragment();
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
        View view = inflater.inflate(R.layout.fragment_policy_buy, container, false);

        btnAccident = view.findViewById(R.id.policy_buy_accident);
        btnAutoInsurance = view.findViewById(R.id.policy_buy_auto_insurance);
        btnMedInsurance = view.findViewById(R.id.policy_buy_med_insurance);
        btnCargoInsurance = view.findViewById(R.id.policy_buy_cargo_insurance);
        btnVzr = view.findViewById(R.id.policy_buy_vzr);

        btnAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of SecondFragment
                AccidentInsuranceFragment accidentInsuranceFragment = new AccidentInsuranceFragment();

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, accidentInsuranceFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        });
        btnAutoInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a new instance of SecondFragment
                AutoInsuranceFragment autoInsuranceFragment = new AutoInsuranceFragment();

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, autoInsuranceFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        });
        btnMedInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of SecondFragment
                BuyDmsFragment buyDmsFragment = new BuyDmsFragment();

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, buyDmsFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        });
        btnCargoInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargoInsuranceFragment cargoInsuranceFragment = new CargoInsuranceFragment();

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, cargoInsuranceFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        });
        btnVzr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VzrFragment vzrFragment = new VzrFragment();

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, vzrFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        });

        return view;
    }
}