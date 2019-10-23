package com.example.acompaaap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.acompaaap.model.Doctor;
import com.example.acompaaap.remote.APIUtils;
import com.example.acompaaap.remote.DoctorService;

import java.util.ArrayList;
import java.util.List;

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

    }
}
