package com.dariahaze.learning_english.ui.tests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.dao.DatabaseAccess;
import com.dariahaze.learning_english.model.RandomQuestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity {

    private  List<RandomQuestion> questionList;
    private List<Integer> answerList = new ArrayList<>();
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private TextView questionTV, answer1TV, answer2TV, answer3TV, explanationTV, correctAnswersTV,
        timeTV, questionNumberTV;
    private Button buttonNext;
    private ImageView check1, check2, check3;
    private int usersAnswer = 0;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture scheduledFuture;
    private int seconds = 30;
    private boolean isAnswerSelected;
    private MaterialDialog exitDialog;
    private boolean showExitDialog = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = findViewById(R.id.toolbarRandomTests);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        questionList = databaseAccess.get20RandomQuestions();
        databaseAccess.close();

        questionTV = findViewById(R.id.randomQuestionTV);
        answer1TV = findViewById(R.id.textViewAnswer1);
        answer2TV = findViewById(R.id.textViewAnswer2);
        answer3TV = findViewById(R.id.textViewAnswer3);
        explanationTV = findViewById(R.id.textViewExplanation);
        correctAnswersTV = findViewById(R.id.textViewCorrectAnswer);
        timeTV = findViewById(R.id.textViewTime30);
        questionNumberTV = findViewById(R.id.textViewAnswerNumber);
        check1 = findViewById(R.id.checkAnswer1);
        check2 = findViewById(R.id.checkAnswer2);
        check3 = findViewById(R.id.checkAnswer3);

        loadQuestion(questionList.get(questionIndex));

        answer1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (questionList.get(questionIndex).getCorrect()==1){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check1.setVisibility(View.VISIBLE);
                    scheduler.shutdown();
                    usersAnswer = 1;
                    showCorrectAnswer(questionList.get(questionIndex));
                    isAnswerSelected = true;
                }
            }
        });

        answer2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (questionList.get(questionIndex).getCorrect()==2){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check2.setVisibility(View.VISIBLE);
                    scheduler.shutdown();
                    usersAnswer = 2;
                    showCorrectAnswer(questionList.get(questionIndex));
                    isAnswerSelected = true;
                }
            }
        });

        answer3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (questionList.get(questionIndex).getCorrect()==3){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check3.setVisibility(View.VISIBLE);
                    scheduler.shutdown();
                    usersAnswer = 3;
                    showCorrectAnswer(questionList.get(questionIndex));
                    isAnswerSelected = true;
                }
            }
        });

        System.out.println("PreScheduler: seconds = "+seconds+" "+new Date());
        scheduledFuture = restartScheduler();

        buttonNext = findViewById(R.id.btnNextRandomQuestion);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNextQuestion();
            }
        });

        exitDialog = new MaterialDialog.Builder(this)
                .title("Are you sure you want to exit?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        scheduler.shutdown();
                        finish();
                    }
                }).build();
    }

    private void moveToNextQuestion(){
        if (!scheduledFuture.isDone()){
            scheduler.shutdown();
        }

        if (questionIndex < questionList.size()-1){

            setDefaultAnswerBackground();
            questionIndex++;
            answerList.add(usersAnswer);
            usersAnswer = 0;
            loadQuestion(questionList.get(questionIndex));
            timeTV.setText(getResources().getString(R.string._00_30));
            scheduledFuture = restartScheduler();
            isAnswerSelected = false;
        } else {
            showExitDialog = false;
            while (answerList.size()!=questionList.size() && answerList.size()<questionList.size()){
                answerList.add(0);
            }
            buttonNext.setVisibility(View.GONE);
            LinearLayout layoutResults = findViewById(R.id.layoutResults);
            layoutResults.setVisibility(View.VISIBLE);
            RecyclerView recyclerView = findViewById(R.id.usersAnswersRV);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            UsersAnswersAdapter adapter = new UsersAnswersAdapter(answerList, questionList);
            recyclerView.setAdapter(adapter);
        }
    }

    private ScheduledFuture restartScheduler(){
        seconds = 30;
        scheduler =  Executors.newSingleThreadScheduledExecutor();
        return scheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {
                        seconds--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String secondsString = seconds+"";
                                if (seconds<10){
                                    secondsString = "0"+secondsString;
                                }
                                timeTV.setText("00:"+secondsString);
                            }
                        });
                        System.out.println("Scheduler: seconds = "+seconds+" "+new Date());
                        if (seconds == 0){
                            scheduler.shutdown();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    moveToNextQuestion();
                                }
                            });
                        }
                    }
                }, 1, 1, TimeUnit.SECONDS);
    }

    private void loadQuestion(RandomQuestion question){
        System.out.println(question);
        int number = questionIndex+1;
        check1.setVisibility(View.INVISIBLE);
        check2.setVisibility(View.INVISIBLE);
        check3.setVisibility(View.INVISIBLE);
        questionNumberTV.setText(number + "/" + questionList.size());
        questionTV.setText(question.getQuestion());
        explanationTV.setVisibility(View.GONE);
        explanationTV.setText(question.getExplanation());

        if (question.getAnswersList().size()==1){
            answer1TV.setVisibility(View.VISIBLE);
            answer1TV.setText(question.getAnswersList().get(0));
            answer2TV.setVisibility(View.GONE);
            answer3TV.setVisibility(View.GONE);
        }
        else if (question.getAnswersList().size()==2){
            answer1TV.setVisibility(View.VISIBLE);
            answer1TV.setText(question.getAnswersList().get(0));
            answer2TV.setVisibility(View.VISIBLE);
            answer2TV.setText(question.getAnswersList().get(1));
            answer3TV.setVisibility(View.GONE);
        }
        else if (question.getAnswersList().size()==3){
            answer1TV.setVisibility(View.VISIBLE);
            answer1TV.setText(question.getAnswersList().get(0));
            answer2TV.setVisibility(View.VISIBLE);
            answer2TV.setText(question.getAnswersList().get(1));
            answer3TV.setVisibility(View.VISIBLE);
            answer3TV.setText(question.getAnswersList().get(2));
        }
        else {
            answer1TV.setVisibility(View.GONE);
            answer2TV.setVisibility(View.GONE);
            answer3TV.setVisibility(View.GONE);
        }

    }

    private void showCorrectAnswer(RandomQuestion question){
        switch (question.getCorrect()){
            case 1:
                answer1TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                break;
            case 2:
                answer1TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                break;
            case 3:
                answer1TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                break;
        }
        explanationTV.setVisibility(View.VISIBLE);
    }

    private void setDefaultAnswerBackground(){
        answer1TV.setBackground(getDrawable(R.drawable.answer_background));
        answer2TV.setBackground(getDrawable(R.drawable.answer_background));
        answer3TV.setBackground(getDrawable(R.drawable.answer_background));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (showExitDialog){
                exitDialog.show();
            }
            else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (showExitDialog) {
            exitDialog.show();
        }
        else {
            finish();
        }
    }
}
