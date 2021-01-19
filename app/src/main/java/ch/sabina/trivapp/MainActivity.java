package ch.sabina.trivapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.sabina.trivapp.data.AnswerListAsyncResponse;
import ch.sabina.trivapp.data.QuestionData;
import ch.sabina.trivapp.model.Question;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView questionTextview;
    private TextView questionCounterTextview;
    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex = 0;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        questionCounterTextview = findViewById(R.id.counter_text);
        questionTextview = findViewById(R.id.question_txt_view);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        questionList = new QuestionData().getQuestions(new AnswerListAsyncResponse() {
           @Override
           public void processFinished(ArrayList<Question> questionArrayList) {
               Log.d(TAG, "request received. Question list size is "+questionArrayList.size());

           }
       });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prevButton:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
                    updateQuestion();
                }
                break;
            case R.id.nextButton:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;
            case R.id.button_true:
                checkAnswer(true);
                updateQuestion();
                break;
            case R.id.button_false:
                checkAnswer(false);
                updateQuestion();
                break;
        }

    }

    private void checkAnswer(boolean b) {
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        questionTextview.setText(question);
        questionCounterTextview.setText(currentQuestionIndex + " / " + questionList.size()); // 0 / 234
    }
}