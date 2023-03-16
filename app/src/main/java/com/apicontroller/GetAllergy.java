package com.apicontroller;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GetAllergy {
    Call<AllergyResponse> getAllergies(
            @Path("patient_id") String patientId,
            @Header("accept") String acceptHeader
    );
}
