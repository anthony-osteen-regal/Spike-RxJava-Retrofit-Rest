package com.regal.spike.RestApi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.regal.spike.RestApi.Services.GitHubRxService;
import com.regal.spike.RestApi.Services.RegalRxService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    TextView log;
    Button repos;
    Button contributors;
    Button echo;

    EditText userName;
    EditText repoName;
    EditText echoMessage;

    int increment;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        increment = 0;
        bind();
    }

    private void bind(){
        log = findViewById(R.id.textViewScrolling);
        log.setMovementMethod(new ScrollingMovementMethod());

        repos = findViewById(R.id.buttonGetRepos);
        repos.setOnClickListener(v -> listRepos());
        repoName = findViewById(R.id.editTextRepo);

        contributors = findViewById(R.id.buttonGetContributors);
        contributors.setOnClickListener(v -> getContributors());
        userName = findViewById(R.id.editTextUser);

        echoMessage = findViewById(R.id.editTextEcho);

        echo = findViewById(R.id.buttonEcho);
        echo.setOnClickListener( v -> getEcho());
    }

    /**
     * This is just test app stuff.
     */
    @SuppressLint("CheckResult")
    public void listRepos(){
        GitHubRxService service = new GitHubRxService();

        String user = userName.getText().toString();
        clearLog();
        appendLog("Showing all repositories for " + user +".\n\n");
        service.getRepos(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::appendLog, this::onError);
    }

    @SuppressLint("CheckResult")
    public void getContributors(){
        GitHubRxService service = new GitHubRxService();

        String user = userName.getText().toString();
        String repo = repoName.getText().toString();
        clearLog();
        appendLog("Showing all contributors for " + user + "'s repository, " + repo +".\n\n");
        service.getContributors(user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::appendLog, this::onError);
    }

    @SuppressLint("CheckResult")
    public void getEcho(){
        RegalRxService service = new RegalRxService();

        String user = echoMessage.getText().toString();
        clearLog();
        appendLog("Echo Response:\n\n");
        service.echo(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::appendLog, this::onError);
    }

    private void onError(Throwable e){
        e.printStackTrace();
        prependLog(e.getMessage() +"\nCheck LogCat\n\n");
    }

    private void clearLog(){
        log.setText("");
    }

    private void appendLog(final String message){
        String contents = log.getText().toString();
        String newLog = contents + '\n' + message;

        log.setText(newLog);
    }
    
    private void prependLog(final String message){
        String contents = log.getText().toString();
        String newLog = message + '\n' + contents;

        log.setText(newLog);
    }

    private void prependLog(final List<String> messages){
        for(String msg : messages){
            prependLog(msg);
        }
    }
}
