package com.example.acompaaap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acompaaap.model.Nurse;
import com.example.acompaaap.remote.APIUtils;
import com.example.acompaaap.remote.NurseService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    Button btnAddNurse;
    Button btnGetNurseList;
    ListView listView;

    NurseService nurseService;
    List<Nurse> list = new ArrayList<Nurse>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnAddNurse = (Button) findViewById(R.id.btnAddNurse);
        btnGetNurseList = (Button) findViewById(R.id.btnGetNurseList);
        listView = (ListView) findViewById(R.id.listView);
        nurseService = APIUtils.getNurseService();

        btnGetNurseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get doctors list
                getNurseList();
            }
        });

        btnAddNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, NurseActivity.class);
                intent.putExtra("name", "");
                startActivity(intent);
            }
        });

    }

    public void getNurseList(){
        Call<List<Nurse>> call = nurseService.getNurses();
        call.enqueue(new Callback<List<Nurse>>() {
            @Override
            public void onResponse(Call<List<Nurse>> call, Response<List<Nurse>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new NurseAdapter(MainActivity2.this, R.layout.list_nurse, list ));
                }
            }

            @Override
            public void onFailure(Call<List<Nurse>> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }
}
