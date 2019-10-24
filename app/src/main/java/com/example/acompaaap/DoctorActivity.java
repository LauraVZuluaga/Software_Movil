package com.example.acompaaap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompaaap.model.Doctor;
import com.example.acompaaap.remote.APIUtils;
import com.example.acompaaap.remote.DoctorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorActivity extends AppCompatActivity {

    DoctorService doctorService;
    EditText edtDId;
    EditText edtDName;
    EditText edtDSpeciality;
    Button btnSave;
    Button btnDelete;
    TextView txtDId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        setTitle("Doctors");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtDId = (TextView) findViewById(R.id.txtDId);
        edtDId = (EditText) findViewById(R.id.edtDId);
        edtDName = (EditText) findViewById(R.id.edtDName);
        edtDSpeciality = (EditText) findViewById(R.id.edtDSpeciality);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDel);

        doctorService = APIUtils.getDoctorService();

        Bundle extras = getIntent().getExtras();
        //La referencia se encuentra en DoctorAdapter en la parte del putExtra
        final String id = extras.getString("Id");
        String name = extras.getString("Name");
        String speciality = extras.getString("Speciality");

        edtDId.setText(id);
        edtDName.setText(name);
        edtDSpeciality.setText(speciality);

        if(id != null && id.trim().length() > 0){
            edtDId.setFocusable(false);
        }else{
            txtDId.setVisibility(View.INVISIBLE);
            edtDId.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doctor doctor = new Doctor();
                doctor.setName(edtDName.getText().toString());
                if(id !=null && id.trim().length() > 0){
                    //update doctor
                    updateDoctor(Integer.parseInt(id), doctor);
                }else{
                    //add doctor
                    addDoctor(doctor);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDoctor(Integer.parseInt(id));

                Intent intent = new Intent(DoctorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addDoctor(Doctor doctor){
        Call<Doctor> call = doctorService.addDoctor(doctor);
        call.enqueue(new Callback<Doctor>() {
            @Override
                public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                    if(response.isSuccessful()){
                        //RECORDATORIO PERSONAL
                        //Toast es un mensaje que se muestra durante unos segundos
                        //Finalmente desaparece después de comunicar el mensaje al usuario
                    Toast.makeText(DoctorActivity.this, "Doctor created successfully", Toast.LENGTH_SHORT).show();
                    }
                }
             @Override
                public void onFailure(Call<Doctor> call, Throwable t) {
                    Log.e("Error: ", t.getMessage());
                }
            });
        }

    public void updateDoctor(int id, Doctor doctor){
        Call<Doctor> call = doctorService.updateDoctor(id, doctor);
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if(response.isSuccessful()){
                    //RECORDATORIO PERSONAL
                    //Toast es un mensaje que se muestra durante unos segundos
                    //Finalmente desaparece después de comunicar el mensaje al usuario
                    Toast.makeText(DoctorActivity.this, "Doctor updated successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    public void deleteDoctor(int id){
        Call<Doctor> call = doctorService.deleteDoctor(id);
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if(response.isSuccessful()){
                    //RECORDATORIO PERSONAL
                    //Toast es un mensaje que se muestra durante unos segundos
                    //Finalmente desaparece después de comunicar el mensaje al usuario
                    Toast.makeText(DoctorActivity.this, "Doctor deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }




}
