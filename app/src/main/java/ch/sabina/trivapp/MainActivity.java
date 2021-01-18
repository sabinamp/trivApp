package ch.sabina.trivapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ch.sabina.trivapp.data.AnswerListAsyncResponse;
import ch.sabina.trivapp.data.QuestionData;
import ch.sabina.trivapp.model.Question;

public class MainActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       new QuestionData().getQuestions(new AnswerListAsyncResponse() {
           @Override
           public void processFinished(ArrayList<Question> questionArrayList) {
               Log.d(TAG, "request received. Question list size is "+questionArrayList.size());

           }
       });
    }


}