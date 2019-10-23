package com.example.acompaaap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.acompaaap.model.Doctor;
import com.example.acompaaap.remote.APIUtils;
import com.example.acompaaap.remote.DoctorService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddDoctor;
    Button btnGetDoctorsList;
    ListView listView;

    DoctorService doctorService;
    List<Doctor> list = new ArrayList<Doctor>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddDoctor = (Button) findViewById(R.id.btnAddDoctor);
        btnGetDoctorsList = (Button) findViewById(R.id.btnGetDoctorsList);
        listView = (ListView) findViewById(R.id.listView);
        doctorService = APIUtils.getDoctorService();

        btnGetDoctorsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get doctors list
            }
        });

    }

    public void getDoctorsList(){
        Call<List<Doctor>> call = doctorService.getDoctors();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new DoctorAdapter(MainActivity.this, R.layout.list_doctor, list ));
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }
}










