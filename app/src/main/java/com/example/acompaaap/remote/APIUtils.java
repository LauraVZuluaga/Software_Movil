package com.example.acompaaap.remote;

public class APIUtils {
    private APIUtils(){

    };

    public static final String API_URL = "http://192.168.0.7:8080/CRUD/Crudmedico/";

    public static DoctorService getDoctorService(){
        return RetrofitClient.getClient(API_URL)
                .create(DoctorService.class);
    }
}
