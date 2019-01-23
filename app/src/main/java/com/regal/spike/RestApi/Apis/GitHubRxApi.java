package com.regal.spike.RestApi.Apis;

import com.regal.spike.RestApi.Models.Contributor;
import com.regal.spike.RestApi.Models.Repository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRxApi {
    @GET("users/{user}/repos")
    Observable<List<Repository>> listRepos(@Path("user") String user);

    @GET("repost/{user}/{repo}/contributors")
    Observable<List<Contributor>> listRepoContributors(
            @Path("user") String user,
            @Path("repo") String repo
    );
}
