package com.example.finalproject.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.models.Request;
import com.example.finalproject.models.TravelPolicy;
import com.example.finalproject.network.ApiService;
import com.example.finalproject.network.RetrofitClient;
import com.example.finalproject.utils.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VzrRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VzrRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VzrRequestFragment() {
        // Required empty public constructor
    }
    private static final int REQUEST_CODE_OPEN_DOCUMENT = 1;



    private TextView textViewMedDocs;
    private TextView textViewTravelDocs;
    private TextView textViewChecks;
    private ActivityResultLauncher<Intent> medDocsPickerLauncher;
    private ActivityResultLauncher<Intent> travelDocsPickerLauncher;
    private ActivityResultLauncher<Intent> checksPickerLauncher;


    private Uri medDocsUri;
    private File medDocsFile;
    private Uri travelDocsUri;
    private Uri checksUri;

    int id;
    EditText summ;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VzrRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VzrRequestFragment newInstance(String param1, String param2) {
        VzrRequestFragment fragment = new VzrRequestFragment();
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



        // Получаем Bundle с данными
        Bundle bundle = getArguments();
        if (bundle != null) {
            String data = bundle.getString("id"); // Здесь "key" должен совпадать с ключом, который использовали для передачи данных
            id = Integer.valueOf(data);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vzr_request, container, false);

        textViewMedDocs = view.findViewById(R.id.vzr_request_med_docs_text);
        textViewTravelDocs = view.findViewById(R.id.vzr_request_travel_docs_text);
        textViewChecks = view.findViewById(R.id.vzr_request_checks_text);

        AppCompatButton buttonMedDocs = view.findViewById(R.id.vzr_request_med_docs);
        AppCompatButton buttonTravelDocs = view.findViewById(R.id.vzr_request_travel_docs);
        AppCompatButton buttonChecks = view.findViewById(R.id.vzr_request_checks);

        buttonMedDocs.setOnClickListener(v -> openFilePicker(medDocsPickerLauncher));
        buttonTravelDocs.setOnClickListener(v -> openFilePicker(travelDocsPickerLauncher));
        buttonChecks.setOnClickListener(v -> openFilePicker(checksPickerLauncher));



        setupFilePickers();


        summ = view.findViewById(R.id.vzr_request_summ);
        view.findViewById(R.id.btn_vzr_request_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String summString = summ.getText().toString();
                    int summInt = Integer.valueOf(summString);

            }
        });
        return view;
    }



    private void setupFilePickers() {
        medDocsPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> handleFilePickerResult(result.getResultCode(), result.getData(), textViewMedDocs)
        );

        travelDocsPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> handleFilePickerResult(result.getResultCode(), result.getData(), textViewTravelDocs)
        );

        checksPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> handleFilePickerResult(result.getResultCode(), result.getData(), textViewChecks)
        );
    }

    private void openFilePicker(ActivityResultLauncher<Intent> launcher) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        launcher.launch(intent);
    }

    private void handleFilePickerResult(int resultCode, @Nullable Intent data, TextView textView) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String fileName = FileUtil.getFileName(uri, getContext());
                textView.setText(fileName);


                // Сохраняем Uri в зависимости от textView
                if (textView == textViewMedDocs) {
                    medDocsUri = uri;
                } else if (textView == textViewTravelDocs) {
                    travelDocsUri = uri;
                } else if (textView == textViewChecks) {
                    checksUri = uri;
                }
            }
        }
    }




    private void sendHealthPayments(Request request) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.healthPayments("Bearer " + AuthFragment.accessToken, request);
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