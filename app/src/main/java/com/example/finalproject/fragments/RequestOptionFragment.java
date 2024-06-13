package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestOptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestOptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RequestOptionFragment() {
        // Required empty public constructor
    }

    AppCompatButton btn_vzr, btn_dms, btn_cargo, btn_auto_insurance;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestOptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestOptionFragment newInstance(String param1, String param2) {
        RequestOptionFragment fragment = new RequestOptionFragment();
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
        View view = inflater.inflate(R.layout.fragment_request_option, container, false);

        btn_vzr = view.findViewById(R.id.btn_request_option_vzr);
        btn_dms = view.findViewById(R.id.btn_request_option_dms);
        btn_cargo = view.findViewById(R.id.btn_request_option_cargo);
        btn_auto_insurance = view.findViewById(R.id.btn_request_option_auto_insurance);

        btn_vzr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_dms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_cargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_auto_insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}