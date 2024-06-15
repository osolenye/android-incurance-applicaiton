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
import com.example.finalproject.models.UserData;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText emailEditText;
    private EditText innEditText;
    private EditText userNameEditText;
    private EditText passwordEditText1;
    private EditText passwordEditText2;
    private EditText accountNumberEditText;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        innEditText = view.findViewById(R.id.innEditText);
        userNameEditText = view.findViewById(R.id.userNameEditText);
        passwordEditText1 = view.findViewById(R.id.passwordEditText1);
        passwordEditText2 = view.findViewById(R.id.passwordEditText2);
        accountNumberEditText = view.findViewById(R.id.accountNumberEditText);

        view.findViewById(R.id.btn_login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });


        view.findViewById(R.id.btn_login_auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of SecondFragment
                AuthFragment authFragment = new AuthFragment();

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, authFragment).addToBackStack(null).commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        });
        return view;


    }

    private void createUser() {
        String email = emailEditText != null ? emailEditText.getText().toString() : "";
        String inn = innEditText != null ? innEditText.getText().toString() : "";
        String userName = userNameEditText != null ? userNameEditText.getText().toString() : "";
        String password1 = passwordEditText1 != null ? passwordEditText1.getText().toString() : "";
        String password2 = passwordEditText2 != null ? passwordEditText2.getText().toString() : "";
        String accountNumber = accountNumberEditText != null ? accountNumberEditText.getText().toString() : "";

        if (password1.equals(password2)) {
            HashMap<String, String> userDataMap = new HashMap<>();
            userDataMap.put("email", email);
            userDataMap.put("inn", inn);
            userDataMap.put("userName", userName);
            userDataMap.put("password1", password1);
            userDataMap.put("password2", password2);
            userDataMap.put("accountNumber", accountNumber);

            UserData userData = new UserData(userDataMap);

            if (!inn.isEmpty() && inn.charAt(0) == '0') {
                Bundle bundle = new Bundle();
                bundle.putSerializable("userData", userData);

                RegOneFragment regOneFragment = new RegOneFragment();
                regOneFragment.setArguments(bundle);

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, regOneFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("userData", userData);

                RegTwoFragment regTwoFragment = new RegTwoFragment();
                regTwoFragment.setArguments(bundle);

                if (getActivity() != null) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, regTwoFragment)
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "Fragment replaced successfully");
                } else {
                    Log.e(TAG, "Activity is null");
                }
            }
        } else {
            Log.e(TAG, "Passwords do not match");
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }
}