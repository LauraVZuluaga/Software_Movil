package com.example.acompaaap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.acompaaap.model.Doctor;

import java.util.List;

public class DoctorAdapter extends ArrayAdapter<Doctor> {

    private Context context;
    private List<Doctor> doctors;

    public DoctorAdapter(@NonNull Context context, int resource, @NonNull List<Doctor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.doctors = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_doctor, parent, false);

        TextView txtDoctorId = (TextView) rowView.findViewById(R.id.txtDoctorId);
        TextView txtDoctorName = (TextView) rowView.findViewById(R.id.txtDoctorName);
        TextView txtDoctorSpeciality = (TextView) rowView.findViewById(R.id.txtDoctorSpeciality);

        txtDoctorId.setText(String.format("#Id: %d", doctors.get(pos).getId()));
        txtDoctorName.setText(String.format("Doctor name: %s", doctors.get(pos).getName()));
        txtDoctorSpeciality.setText(String.format("Speciality: %s", doctors.get(pos).getSpecialty()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Doctors form
                Intent intent = new Intent(context, DoctorActivity.class);
                intent.putExtra("Id", String.valueOf(doctors.get(pos).getId()));
                intent.putExtra("Name", doctors.get(pos).getName());
                intent.putExtra("Speciality", doctors.get(pos).getSpecialty());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
