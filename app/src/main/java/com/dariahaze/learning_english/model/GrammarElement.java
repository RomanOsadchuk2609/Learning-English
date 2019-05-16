package com.dariahaze.learning_english.model;

import com.dariahaze.learning_english.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GrammarElement {
    private String name = "";
    private String path = "";
    private List<GrammarElement> subElements = new ArrayList<>();

    public GrammarElement(String name) {
        this.name = name;
        this.path = name;
    }
    public GrammarElement(String name, GrammarElement parent) {
        this.name = name;
    }

    public GrammarElement(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public GrammarElement(String name, String path, List<GrammarElement> subElements) {
        this.name = name;
        this.path = path;
        this.subElements = subElements;
    }

    public GrammarElement(String name, List<GrammarElement> subElements) {
        this.name = name;
        this.subElements = subElements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<GrammarElement> getSubElements() {
        return subElements;
    }

    public void setSubElements(List<GrammarElement> subElements) {
        this.subElements = subElements;
    }
}
