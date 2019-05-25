package com.dariahaze.learning_english.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersStatistics {
    private String topics;
    private String tests;
    private String cardSets;
}
