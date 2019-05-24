package com.dariahaze.learning_english.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeTest {
    @JsonAlias("Mainhd")
    private String header;

    @JsonAlias("Sub")
    private List<PracticeQuestion> questions;
}
