package com.regal.spike.RestApi.Services;

import com.regal.spike.RestApi.Models.Contributor;
import com.regal.spike.RestApi.Apis.GitHubRxApi;
import com.regal.spike.RestApi.Models.Repository;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRxService {
    private GitHubRxApi gitHubRxApi;

    public GitHubRxService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        gitHubRxApi = retrofit.create(GitHubRxApi.class);
    }

//    public Observable<String> getTopContributors(String userName) {
//        return gitHubRxApi.listRepos(userName)
//                .flatMapIterable(x -> x)
//                .flatMap(repo -> gitHubRxApi.listRepoContributors(userName, repo.getName()))
//                .flatMapIterable(x->x)
//                //.filter(c -> c.getContributions() > 100)
//                .sorted((a, b) -> b.getContributions() - a.getContributions())
//                .map(Contributor::getName)
//                .distinct();
//    }

    public Observable<String> getTopContributors(String userName) {
        return gitHubRxApi.listRepos(userName)
                .flatMapIterable(x -> x)
                .flatMap(repo -> gitHubRxApi.listRepoContributors(userName, repo.getName()))
                .flatMapIterable(x -> x)
                .sorted((a, b) -> b.getContributions() - a.getContributions())
                .map(Contributor::getName)
                .distinct();
    }

    //Don't trust this
    public Observable<String> getRepos(String userName) {
        return gitHubRxApi.listRepos(userName)
                .flatMapIterable(x -> x)
                .map(Repository::getName)
                .distinct();
    }
}
