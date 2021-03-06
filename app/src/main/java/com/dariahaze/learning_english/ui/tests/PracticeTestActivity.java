package com.dariahaze.learning_english.ui.tests;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.dariahaze.learning_english.model.PracticeQuestion;
import com.dariahaze.learning_english.model.PracticeTest;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PracticeTestActivity extends AppCompatActivity {
    private PracticeTest practiceTest;
    private List<Integer> answerList = new ArrayList<>();
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private TextView questionTV, answer1TV, answer2TV, answer3TV, correctAnswersTV, headerTV,
            header2TV,timeTV, questionNumberTV, answer4TV, topicTV;
    private Button buttonNext;
    private ImageView check1, check2, check3, check4;
    private int usersAnswer = 0;
    private ScheduledExecutorService scheduler;
    private ScheduledFuture scheduledFuture;
    private long seconds;
    private boolean isAnswerSelected;
    boolean showExitDialog = true;
    private MaterialDialog exitDialog, resultDialog;
    private DatabaseReference bestScoreReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Integer bestScore = null;
    private DatabaseReference statisticsReference;
    int tests = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Bundle bundle = getIntent().getExtras();
        practiceTest = (PracticeTest) bundle.get("Test");

        statisticsReference = FirebaseDatabase.getInstance().getReference("statistics/"
                + Utils.getFormattedUserKey(currentUser.getEmail()));

        statisticsReference.child("tests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer amountOfTests = dataSnapshot.getValue(Integer.class);
                if (amountOfTests!=null){
                    tests = amountOfTests;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bestScoreReference = FirebaseDatabase.getInstance().getReference("bestScores/"
                + Utils.getFormattedUserKey(currentUser.getEmail())+"/"+bundle.getString("Topic"));

        bestScoreReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bestScore = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarPracticeTests);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        headerTV = findViewById(R.id.practiceHeaderTV);
        header2TV = findViewById(R.id.practiceHeader2TV);
        topicTV = findViewById(R.id.topicPracticeTV);
        questionTV = findViewById(R.id.practiceQuestionTV);
        answer1TV = findViewById(R.id.practiceAnswer1);
        answer2TV = findViewById(R.id.practiceAnswer2);
        answer3TV = findViewById(R.id.practiceAnswer3);
        answer4TV = findViewById(R.id.practiceAnswer4);
        correctAnswersTV = findViewById(R.id.practiceCorrectAnswerTV);
        timeTV = findViewById(R.id.textViewTimePractice30);
        questionNumberTV = findViewById(R.id.textViewAnswerNumberPractice);
        check1 = findViewById(R.id.checkPracticeAnswer1);
        check2 = findViewById(R.id.checkPracticeAnswer2);
        check3 = findViewById(R.id.checkPracticeAnswer3);
        check4 = findViewById(R.id.checkPracticeAnswer4);

        topicTV.setText(bundle.getString("Topic"));
        headerTV.setText(practiceTest.getHeader());
        header2TV.setText(practiceTest.getHeader());
        seconds = practiceTest.getMinutes()*60;
        setTimeTV(seconds);

        loadQuestion(practiceTest.getQuestions().get(questionIndex));

        answer1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (practiceTest.getQuestions().get(questionIndex).getCorrect()==1){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check1.setVisibility(View.VISIBLE);
                    usersAnswer = 1;
                    answerList.add(usersAnswer);
                    showCorrectAnswer(practiceTest.getQuestions().get(questionIndex));
                    isAnswerSelected = true;
                    checkOnLastQuestion();
                }
            }
        });

        answer2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (practiceTest.getQuestions().get(questionIndex).getCorrect()==2){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check2.setVisibility(View.VISIBLE);
                    usersAnswer = 2;
                    answerList.add(usersAnswer);
                    showCorrectAnswer(practiceTest.getQuestions().get(questionIndex));
                    isAnswerSelected = true;
                    checkOnLastQuestion();
                }
            }
        });

        answer3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (practiceTest.getQuestions().get(questionIndex).getCorrect()==3){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check3.setVisibility(View.VISIBLE);
                    usersAnswer = 3;
                    answerList.add(usersAnswer);
                    showCorrectAnswer(practiceTest.getQuestions().get(questionIndex));
                    isAnswerSelected = true;
                    checkOnLastQuestion();
                }
            }
        });

        answer4TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected){
                    if (practiceTest.getQuestions().get(questionIndex).getCorrect()==4){
                        correctAnswers++;
                        correctAnswersTV.setText(correctAnswers+"");
                    }
                    check4.setVisibility(View.VISIBLE);
                    usersAnswer = 4;
                    answerList.add(usersAnswer);
                    showCorrectAnswer(practiceTest.getQuestions().get(questionIndex));
                    isAnswerSelected = true;
                    checkOnLastQuestion();
                }
            }
        });

        System.out.println("PreScheduler: seconds = "+seconds+" "+new Date());
        scheduledFuture = restartScheduler();

        buttonNext = findViewById(R.id.btnNextPracticeQuestion);
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
                        if (correctAnswers > practiceTest.getBestScore()){
                            practiceTest.setBestScore(correctAnswers);
                            bestScoreReference.setValue(correctAnswers);
                        }
                        scheduler.shutdown();
                        finish();
                    }
                }).build();

    }

    private void setTimeTV(long seconds){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(seconds*1000);
        System.out.println("SET TIME: "+date);
        timeTV.setText(dateFormat.format(date));
    }

    private ScheduledFuture restartScheduler(){
        seconds = practiceTest.getMinutes()*60;
        setTimeTV(seconds);
        scheduler =  Executors.newSingleThreadScheduledExecutor();
        return scheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {
                        seconds--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTimeTV(seconds);
                            }
                        });
                        System.out.println("Scheduler: seconds = "+seconds+" "+new Date());
                        if (seconds == 0){
                            scheduler.shutdown();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    finishTest();
                                }
                            });
                        }
                    }
                }, 1, 1, TimeUnit.SECONDS);
    }


    private void moveToNextQuestion(){
        if (questionIndex < practiceTest.getQuestions().size()-1){
            isAnswerSelected = false;
            setDefaultAnswerBackground();
            questionIndex++;
            usersAnswer = 0;
            loadQuestion(practiceTest.getQuestions().get(questionIndex));
        } else {
            finishTest();
        }
    }

    private void checkOnLastQuestion(){
        if (questionIndex == practiceTest.getQuestions().size()){
            finishTest();
        }
    }

    private void finishTest(){
        showExitDialog = false;
        if (correctAnswers > practiceTest.getBestScore()){
            practiceTest.setBestScore(correctAnswers);
            bestScoreReference.setValue(correctAnswers);

            if (correctAnswers == practiceTest.getBestScore()){
                tests++;
                statisticsReference.child("tests").setValue(tests);
            }
        }
        resultDialog = new MaterialDialog.Builder(this)
                .title("You got " + correctAnswers + " of " + practiceTest.getQuestions().size())
                .content("Best Score: "+practiceTest.getBestScore())
                .positiveText("Exit")
                .negativeText("Review")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        scheduler.shutdown();
                        finish();
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        scheduler.shutdown();
                        showResult();
                    }
                }).show();
    }

    private void showResult(){
        while (answerList.size() != practiceTest.getQuestions().size()
                && answerList.size() < practiceTest.getQuestions().size()){
            answerList.add(0);
        }

        buttonNext.setVisibility(View.GONE);
        LinearLayout layoutResults = findViewById(R.id.layoutPracticeResults);
        layoutResults.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.practiceAnswersRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        PracticeAnswersAdapter adapter = new PracticeAnswersAdapter(answerList, practiceTest);
        recyclerView.setAdapter(adapter);
    }

    private void loadQuestion(PracticeQuestion question){
        System.out.println(question);
        int number = questionIndex+1;
        check1.setVisibility(View.INVISIBLE);
        check2.setVisibility(View.INVISIBLE);
        check3.setVisibility(View.INVISIBLE);
        check4.setVisibility(View.INVISIBLE);
        questionNumberTV.setText(number + "/" + practiceTest.getQuestions().size());
        questionTV.setText(question.getQuestion());

        if (question.getAmountOfAnswers()==2){
            answer1TV.setVisibility(View.VISIBLE);
            answer1TV.setText(question.getAnswer1());
            answer2TV.setVisibility(View.VISIBLE);
            answer2TV.setText(question.getAnswer2());
            answer3TV.setVisibility(View.GONE);
            answer4TV.setVisibility(View.GONE);
        }
        else if (question.getAmountOfAnswers()==3){
            answer1TV.setVisibility(View.VISIBLE);
            answer1TV.setText(question.getAnswer1());
            answer2TV.setVisibility(View.VISIBLE);
            answer2TV.setText(question.getAnswer2());
            answer3TV.setVisibility(View.VISIBLE);
            answer3TV.setText(question.getAnswer3());
            answer4TV.setVisibility(View.GONE);
        }
        else if (question.getAmountOfAnswers()==4){
            answer1TV.setVisibility(View.VISIBLE);
            answer1TV.setText(question.getAnswer1());
            answer2TV.setVisibility(View.VISIBLE);
            answer2TV.setText(question.getAnswer2());
            answer3TV.setVisibility(View.VISIBLE);
            answer3TV.setText(question.getAnswer3());
            answer4TV.setVisibility(View.VISIBLE);
            answer4TV.setText(question.getAnswer4());
        }
        else {
            answer1TV.setVisibility(View.GONE);
            answer2TV.setVisibility(View.GONE);
            answer3TV.setVisibility(View.GONE);
            answer4TV.setVisibility(View.GONE);
        }

    }

    private void showCorrectAnswer(PracticeQuestion question){
        switch (question.getCorrect()){
            case 1:
                answer1TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer4TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                break;
            case 2:
                answer1TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer4TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                break;
            case 3:
                answer1TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                answer4TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                break;
            case 4:
                answer1TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer2TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer3TV.setBackground(getDrawable(R.drawable.wrong_answer_background));
                answer4TV.setBackground(getDrawable(R.drawable.correct_answer_background));
                break;
        }
    }

    private void setDefaultAnswerBackground(){
        answer1TV.setBackground(getDrawable(R.drawable.answer_background));
        answer2TV.setBackground(getDrawable(R.drawable.answer_background));
        answer3TV.setBackground(getDrawable(R.drawable.answer_background));
        answer4TV.setBackground(getDrawable(R.drawable.answer_background));
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
