package com.apicontroller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;


public interface GetAppointments {
    @GET("/apis/default/api/patient/{patient_id}/appointment")
    Call<AppointmentResponse> getAppointments(
            @Path("patient_id") String patientId,
            @Header("accept") String acceptHeader
    );

}
