package com.dariahaze.learning_english.ui.tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.dao.DatabaseAccess;
import com.dariahaze.learning_english.model.RandomQuestion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    private  List<RandomQuestion> questionList;
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private TextView questionTV, answer1TV, answer2TV, answer3TV, explanationTV, correctAnswersTV,
    timeTV, questionNumberTV;
    private Button buttonNext;
    private Map<Integer,RandomQuestion> usersAnswers = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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


        loadQuestion(questionList.get(questionIndex));

        answer1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionList.get(questionIndex).getCorrect()==1){
                    correctAnswers++;
                    correctAnswersTV.setText(correctAnswers+"");
                }
                usersAnswers.put(1,questionList.get(questionIndex));
                showCorrectAnswer(questionList.get(questionIndex));
            }
        });

        answer2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionList.get(questionIndex).getCorrect()==2){
                    correctAnswers++;
                    correctAnswersTV.setText(correctAnswers+"");
                }
                usersAnswers.put(2,questionList.get(questionIndex));
                showCorrectAnswer(questionList.get(questionIndex));
            }
        });

        answer3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionList.get(questionIndex).getCorrect()==3){
                    correctAnswers++;
                    correctAnswersTV.setText(correctAnswers+"");
                }
                usersAnswers.put(3,questionList.get(questionIndex));
                showCorrectAnswer(questionList.get(questionIndex));
            }
        });

        buttonNext = findViewById(R.id.btnNextRandomQuestion);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex < questionList.size()-1){
                    setDefaultAnswerBackground();
                    questionIndex++;
                    loadQuestion(questionList.get(questionIndex));
                }
            }
        });


    }

    private void loadQuestion(RandomQuestion question){
        System.out.println(question);
        timeTV.setText(getResources().getString(R.string._00_30));
        int number = questionIndex+1;
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
}
