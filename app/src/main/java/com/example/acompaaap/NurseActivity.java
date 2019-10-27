package com.example.acompaaap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acompaaap.model.Nurse;
import com.example.acompaaap.remote.APIUtils;
import com.example.acompaaap.remote.NurseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NurseActivity extends AppCompatActivity {
    NurseService nurseService;
    EditText edtNId;
    EditText edtNName;
    EditText edtNAddress;
    EditText edtNSalary;
    Button btnSave;
    Button btnDelete;
    TextView txtNId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        setTitle("Nurse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtNId = (TextView) findViewById(R.id.txtNId);
        edtNId = (EditText) findViewById(R.id.edtNId);
        edtNName = (EditText) findViewById(R.id.edtNName);
        edtNAddress = (EditText) findViewById(R.id.edtNAddress);
        edtNSalary = (EditText) findViewById((R.id.edtNSalary));
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDel);

        nurseService = APIUtils.getNurseService();

        Bundle extras = getIntent().getExtras();
        //La referencia se encuentra en DoctorAdapter en la parte del putExtra
        final String id = extras.getString("Id");
        String name = extras.getString("Name");
        String address = extras.getString("Address");
        String salary = extras.getString("Salary");

        edtNId.setText(id);
        edtNName.setText(name);
        edtNAddress.setText(address);
        edtNSalary.setText(salary);

        if(id != null && id.trim().length() > 0){
            edtNId.setFocusable(false);
        }else{
            txtNId.setVisibility(View.INVISIBLE);
            edtNId.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nurse nurse = new Nurse();
                nurse.setName(edtNName.getText().toString());
                if(id !=null && id.trim().length() > 0){
                    //update doctor
                    updateNurse(Integer.parseInt(id), nurse);
                }else{
                    //add doctor
                    addDoctor(nurse);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNurse(Integer.parseInt(id));

                Intent intent = new Intent(NurseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addDoctor(Nurse doctor){
        Call<Nurse> call = nurseService.addNurse(doctor);
        call.enqueue(new Callback<Nurse>() {
            @Override
            public void onResponse(Call<Nurse> call, Response<Nurse> response) {
                if(response.isSuccessful()){
                    //RECORDATORIO PERSONAL
                    //Toast es un mensaje que se muestra durante unos segundos
                    //Finalmente desaparece después de comunicar el mensaje al usuario
                    Toast.makeText(NurseActivity.this, "Doctor created successfully", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Nurse> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    public void updateNurse(int id, Nurse nurse){
        Call<Nurse> call = nurseService.updateNurse(id, nurse);
        call.enqueue(new Callback<Nurse>() {
            @Override
            public void onResponse(Call<Nurse> call, Response<Nurse> response) {
                if(response.isSuccessful()){
                    //RECORDATORIO PERSONAL
                    //Toast es un mensaje que se muestra durante unos segundos
                    //Finalmente desaparece después de comunicar el mensaje al usuario
                    Toast.makeText(NurseActivity.this, "Doctor updated successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Nurse> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    public void deleteNurse(int id){
        Call<Nurse> call = nurseService.deleteNurse(id);
        call.enqueue(new Callback<Nurse>() {
            @Override
            public void onResponse(Call<Nurse> call, Response<Nurse> response) {
                if(response.isSuccessful()){
                    //RECORDATORIO PERSONAL
                    //Toast es un mensaje que se muestra durante unos segundos
                    //Finalmente desaparece después de comunicar el mensaje al usuario
                    Toast.makeText(NurseActivity.this, "Doctor deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Nurse> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

}
