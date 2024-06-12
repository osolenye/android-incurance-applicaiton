package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.items.MyAdapter;
import com.example.finalproject.models.UserData;
import com.example.finalproject.network.ApiService;
import com.example.finalproject.network.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AppCompatButton dropdown_open;
    private LinearLayout dropdown_content;
    private AppCompatButton dropdown_close;

    private MaterialButton button_processed_requests;
    private MaterialButton button_unprocessed_requests;



    private RecyclerView recyclerView;
    private MyAdapter adapter;
    public ProfileFragment() {
        // Required empty public constructor
    }

    private Map<String, Object> user;
    private List<Map<String, Object>> policies;
    private TextView username, userInn;
    private String accessToken;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
//        return inflater.inflate(R.layout.fragment_profile, container, false);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (getArguments() != null) {
            accessToken = (String) getArguments().getSerializable("accessToken");
            Toast.makeText(getContext(), accessToken, Toast.LENGTH_SHORT).show();
        }
        getProfileData(accessToken);


        username = view.findViewById(R.id.profile_username);
        userInn = view.findViewById(R.id.profile_user_inn);



        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<String> data = Arrays.asList("Item 1", "Item 2", "Item 3"); // Пример данных
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

        dropdown_open = view.findViewById(R.id.dropdown_open_button);
        dropdown_content = view.findViewById(R.id.dropdown_content);
        dropdown_close = view.findViewById(R.id.dropdown_close);

        dropdown_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdown_open.setVisibility(View.GONE);
                dropdown_content.setVisibility(View.VISIBLE);
                dropdown_close.setVisibility(View.VISIBLE);
            }
        });
        dropdown_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdown_open.setVisibility(View.VISIBLE);
                dropdown_content.setVisibility(View.GONE);
                dropdown_close.setVisibility(View.GONE);
            }
        });

        button_processed_requests = view.findViewById(R.id.button_processed_requests);
        button_unprocessed_requests = view.findViewById(R.id.button_unprocessed_requests);
        button_processed_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonGreen(button_processed_requests);
                changeButtonWhite(button_unprocessed_requests);
            }
        });
        button_unprocessed_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonGreen(button_unprocessed_requests);
                changeButtonWhite(button_processed_requests);
            }
        });



        return view;
    }


    private List<String> createDummyData() {
        // Здесь вы можете создать и вернуть список данных для вашего RecyclerView
        // Например, если у вас есть список строк:
        List<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        // и так далее
        return data;
    }


    private void changeButtonGreen(MaterialButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.background_green));
        button.setTextColor(getResources().getColor(R.color.background_yellow));
    }
    private void changeButtonWhite(MaterialButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.white));
        button.setTextColor(getResources().getColor(R.color.black));
    }



    private void getProfileData(String accessToken) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Map<String, Object>> call = apiService.getProfile("Bearer " + accessToken);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Request successful and response is not null
                    Map<String, Object> profileResponse = response.body();

                    // Get user data
                    user = (Map<String, Object>) profileResponse.get("user");
                    // Get policies data
                    policies = (List<Map<String, Object>>) profileResponse.get("policies");

                    username.setText(user.get("username").toString());
                    userInn.setText(user.get("inn").toString());
                } else {
                    // Request failed
                    Toast.makeText(getContext(), "Request failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                // Network error
                Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}