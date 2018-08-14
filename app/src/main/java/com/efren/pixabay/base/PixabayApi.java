package com.efren.pixabay.base;

import com.efren.pixabay.model.ImageRequestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by efren.lamolda on 07.08.18.
 */

public interface PixabayApi {

    @GET("/api")
    Observable<ImageRequestResponse> getImages(@Query("key") String key,
                                               @Query("q") String query,
                                               @Query("image_type") String imageType);
}
