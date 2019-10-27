package com.example.acompaaap.remote;

import com.example.acompaaap.model.Doctor;
import com.example.acompaaap.model.Nurse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NurseService {

    //Revisar establecimiento de las rutas
    @GET("get/")
    Call<List<Nurse>> getNurses();

    @POST("add/")
    Call<Nurse> addNurse(@Body Nurse nurse);

    @PUT("update/(id)")
    Call<Nurse> updateNurse(@Path("id") int id, @Body Nurse nurse);

    @DELETE("delete/(id)")
    Call<Nurse> deleteNurse(@Path("id") int id);

}

