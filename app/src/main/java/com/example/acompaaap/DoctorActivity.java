package com.example.acompaaap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.acompaaap.model.Doctor;
import com.example.acompaaap.remote.APIUtils;
import com.example.acompaaap.remote.DoctorService;

import retrofit2.Call;

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
                }else{
                    //add doctor
                }
            }
        });

    }
        public void addDoctor(Doctor doctor){
            Call<Doctor> call = doctorService.addDoctor(doctor);
        }

}
