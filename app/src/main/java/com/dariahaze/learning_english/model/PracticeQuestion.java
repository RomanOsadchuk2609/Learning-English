package com.dariahaze.learning_english.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeQuestion {
    @JsonAlias("quetn")
    private String question;

    @JsonAlias("optns")
    private String options;

    @JsonAlias("1")
    private String option1;

    @JsonAlias("2")
    private String option2;

    @JsonAlias("3")
    private String option3;

    @JsonAlias("4")
    private String option4;

    @JsonAlias("Answr")
    private String correct;
}
