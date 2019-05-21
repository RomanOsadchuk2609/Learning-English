package com.dariahaze.learning_english.model;

public class RandomQuestion {
    private long id;
    private String question;
    private String answer;
    private String explanation;
    private String correct;

    public RandomQuestion() {
    }

    public RandomQuestion(long id, String question, String answer, String explanation, String correct) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.explanation = explanation;
        this.correct = correct;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
