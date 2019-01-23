package com.regal.spike.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubBasicApi {

    @GET("users/{user}/repos")
    Call<List<Repository>> listRepos(@Path("user") String user);

    @GET("repost/{user}/{repo}/contributors")
   Call<List<Contributor>> listRepoContributors(
            @Path("user") String user,
            @Path("repo") String repo
    );
}
