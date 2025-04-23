package com.example.seedshop.network;

import com.example.seedshop.model.FirebaseDocument;
import com.example.seedshop.model.FirebaseListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FirestoreRestService {
    @GET("v1/projects/{projectId}/databases/(default)/documents/seeds")
    Call<FirebaseListResponse> listSeeds(
            @Path("projectId") String projectId,
            @Query("key") String apiKey
    );
}
