package com.regal.spike.RestApi.Services;

import com.regal.spike.RestApi.Models.Contributor;
import com.regal.spike.RestApi.Apis.GitHubRxApi;
import com.regal.spike.RestApi.Models.Repository;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRxService {
    private GitHubRxApi gitHubRxApi;

    public GitHubRxService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        gitHubRxApi = retrofit.create(GitHubRxApi.class);
    }

    public Observable<String> getTopContributors(String userName) {
        return gitHubRxApi.listRepos(userName)
                .flatMapIterable(x -> x)
                .flatMap(repo -> gitHubRxApi.listRepoContributors(userName, repo.getName()))
                .flatMapIterable(x->x)
                //.filter(c -> c.getContributions() > 100)
                .sorted((a, b) -> b.getContributions() - a.getContributions())
                .map(Contributor::getName)
                .distinct();
    }

    /**
     * @param userName the owner of the repository
     * @param repoName the name of the repository
     * @return the names of all the contributors for a given repo
     */
    public Observable<String> getContributors(String userName, String repoName){
        return gitHubRxApi.listRepoContributors(userName, repoName)
                .flatMapIterable(x->x)
                .map(Contributor::toString)
                .distinct();
    }

    /**
     * @param userName The username who's repository list we're querying
     * @return the names of all the repos created by the provided user
     */
    public Observable<String> getRepos(String userName) {
        return gitHubRxApi.listRepos(userName)
                .flatMapIterable(x -> x)
                .map(Repository::toString)
                .distinct();
    }
}
