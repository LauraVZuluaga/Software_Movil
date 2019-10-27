package com.example.acompaaap;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.acompaaap.model.Doctor;
import com.example.acompaaap.model.Nurse;

import org.w3c.dom.Text;

import java.util.List;

    public class NurseAdapter extends ArrayAdapter<Nurse> {

        private Context context;
        private List<Nurse> nurses;

        public NurseAdapter(@NonNull Context context, int resource, @NonNull List<Nurse> objects) {
            super(context, resource, objects);
            this.context = context;
            this.nurses = objects;
        }

        @Override
        public View getView(final int pos, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.list_nurse, parent, false);

            TextView txtNurseId = (TextView) rowView.findViewById(R.id.txtNurseId);
            TextView txtNurseName = (TextView) rowView.findViewById(R.id.txtNurseName);
            TextView txtNurseAddress = (TextView) rowView.findViewById(R.id.txtNurseAddress);
            TextView txtNurseSalary = (TextView) rowView.findViewById(R.id.txtNurseSalary);

            txtNurseId.setText(String.format("#Id: %d", nurses.get(pos).getId()));
            txtNurseName.setText(String.format("Name: %s", nurses.get(pos).getName()));
            txtNurseAddress.setText(String.format("Address: %s", nurses.get(pos).getAddress()));
            txtNurseSalary.setText(String.format("Salary:  %d", nurses.get(pos).getSalary()));

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //start Activity Doctors form
                    Intent intent = new Intent(context, NurseActivity.class);
                    intent.putExtra("Id", String.valueOf(nurses.get(pos).getId()));
                    intent.putExtra("Name", nurses.get(pos).getName());
                    intent.putExtra("Speciality", nurses.get(pos).getAddress());
                    intent.putExtra("Salary", nurses.get(pos).getSalary());
                    context.startActivity(intent);
                }
            });
            return rowView;
        }
    }

