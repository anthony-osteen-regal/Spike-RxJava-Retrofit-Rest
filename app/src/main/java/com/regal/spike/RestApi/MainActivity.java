package com.regal.spike.RestApi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        call.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementWright();
            }
        });
    }

    /**
     * This is just test app stuff.
     */
    public void incrementWright(){
        appendToLog("You pressed a button! " + String.valueOf(increment));
        increment += 1;
    }

    private void appendToLog(final String message){
        String contents = log.getText().toString();
        String newLog = message + '\n' + contents;

        log.setText(newLog);
    }
}
