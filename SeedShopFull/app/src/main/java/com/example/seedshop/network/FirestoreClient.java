package com.example.seedshop.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirestoreClient {
    private static FirestoreRestService svc;
    public static FirestoreRestService getService() {
        if (svc == null) {
            svc = new Retrofit.Builder()
                    .baseUrl("https://firestore.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FirestoreRestService.class);
        }
        return svc;
    }
}