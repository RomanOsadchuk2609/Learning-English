package com.dariahaze.learning_english.model;

import java.util.ArrayList;
import java.util.List;

public class RandomQuestion {
    private long id;
    private String question;
    private String answer;
    private String explanation;
    private int correct;
    private List<String> answersList;

    public RandomQuestion() {

    }

    public RandomQuestion(long id, String question, String answer, String explanation, String correct) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.explanation = explanation;
        this.correct = Integer.valueOf(correct);
        createListOfAnswers();
    }

    @Override
    public String toString() {
        return "RandomQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", explanation='" + explanation + '\'' +
                ", correct='" + correct + '\'' +
                '}';
    }

    public void createListOfAnswers(){
        List<String> list = new ArrayList<>();
        String tempAnswer = answer;
        while (tempAnswer.contains("####")){
            String currentAnswer = tempAnswer.substring(0,tempAnswer.indexOf("#"));
            list.add(currentAnswer);
            tempAnswer = tempAnswer.replaceFirst(currentAnswer,"");
            tempAnswer = tempAnswer.replaceFirst("####","");
        }
        list.add(tempAnswer);
        answersList = list;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public int getCorrect() {
        return correct;
    }

    public List<String> getAnswersList() {
        return answersList;
    }
}
