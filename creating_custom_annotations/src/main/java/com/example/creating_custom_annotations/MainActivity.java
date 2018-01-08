package com.example.creating_custom_annotations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        String str = "";
        Class foo = Foo.class;
        for (Method method : foo.getMethods()) {
            Status statusAnnotation = (Status) method.getAnnotation(Status.class);
            if (statusAnnotation != null) {
                str += "Method Name : " + method.getName() + "\n" +
                "Author : " + statusAnnotation.author() + "\n" +
                "Priority : " + statusAnnotation.priority() + "\n" +
                "Completion Status : " + statusAnnotation.completion() + "\n";
            } else {
                str += "*** " + method.getName() + " ***\n";
                Log.e("LOG_TAG","ELSE");
            }
            str += "\n";
        }
        textView.setText(str);
    }
}
