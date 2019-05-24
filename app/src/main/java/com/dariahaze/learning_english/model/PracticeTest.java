package com.dariahaze.learning_english.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeTest implements Serializable {
    @JsonAlias("Mainhd")
    private String header;

    @JsonAlias("Sub")
    private List<PracticeQuestion> questions;

    @JsonAlias("minutes")
    private int minutes;

    private int bestScore = 0;
}
