package com.regal.spike.RestApi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import com.regal.spike.RestApi.Services.GitHubRxService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    TextView log;
    Button call;
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
        call = findViewById(R.id.buttonCall);

        call.setOnClickListener(v -> listRepos());
    }

    /**
     * This is just test app stuff.
     */
    public void listRepos(){
        GitHubRxService service = new GitHubRxService();

        //Yay it works!!
//        service.getRepos("anthonyPse")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::appendToLog, this::onError);

        service.getTopContributors("eugenp")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::appendToLog, this::onError);

    }

    private void onError(Throwable e){
        e.printStackTrace();
        appendToLog(e.getMessage() +"\nCheck LogCat\n\n");
    }
    private void appendToLog(final String message){
        String contents = log.getText().toString();
        String newLog = message + '\n' + contents;

        log.setText(newLog);
    }

    private void appendToLog(final List<String> messages){
        for(String msg : messages){
            appendToLog(msg);
        }
    }
}
