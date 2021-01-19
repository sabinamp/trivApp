package ch.sabina.trivapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private CardView cardView;

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
        cardView = findViewById(R.id.cardView);

        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        questionList = new QuestionData().getQuestions(
                questionArrayList -> Log.d(TAG, "request received. Question list size is "+questionArrayList.size()));
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

    private void checkAnswer(boolean bInput) {
        boolean correctAnswer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMessageId;
        if (bInput == correctAnswer) {

            fadeView();
            toastMessageId = R.string.correct_answer;
        } else {
            executeShakeAnimation();
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT)
                .show();
    }



    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        questionTextview.setText(question);
        questionCounterTextview.setText(currentQuestionIndex + " / " + questionList.size()); // 0 / 234
    }

    private void executeShakeAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake_anim);

        cardView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void fadeView() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);

        alphaAnimation.setDuration(350);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}