package com.endpointfunctions;

import com.apicontroller.AppointmentResponse;
import com.apicontroller.GetAppointments;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuncGetAppointment {
    public void createGetAppointments(String token, String pid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mobileapp.trackdemon.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAppointments getAppointment = retrofit.create(GetAppointments.class);
        Call <List<AppointmentResponse>>call = getAppointment.getAppointments(
                pid,
                "application/json",
                "Bearer " + token
        );


        call.enqueue(new Callback<List<AppointmentResponse>>() {
            @Override
            public void onResponse(Call<List<AppointmentResponse>> call, Response<List<AppointmentResponse>> response) {
                if (response.isSuccessful()) {

                    List<AppointmentResponse> appointments = response.body();
                    for (AppointmentResponse appointment : appointments) {
                        String pcEventDate = appointment.getPcEventDate();
                        System.out.println("pc_eventDate: " + pcEventDate);
                    }
                    //Handle the successful response, e.g., update UI or store data
                } else {
                    //Handle the error response, e.g., display an error message
                    System.out.println("Request Not");
                }
            }

            @Override
            public void onFailure(Call<List<AppointmentResponse>> call, Throwable t) {
                //Handle the failure, e.g., log the error or display a message
                System.out.println("No Network");
            }
        });



    }

}
