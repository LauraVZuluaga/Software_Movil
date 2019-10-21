package com.example.acompaaap.remote;

import com.example.acompaaap.model.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DoctorService {

    @GET("doctor/")
    Call<List<Doctor>> getDoctors();

    @POST("add/")
    Call<Doctor> addDoctor(@Body Doctor doctor);

    @PUT("update/(id)")
    Call<Doctor> updateDoctor(@Path("id") int id, @Body Doctor doctor);

    @DELETE("delete/(id)")
    Call<Doctor> deleteDoctor(@Path("id") int id);

}
