package com.dariahaze.learning_english.utils;

import com.dariahaze.learning_english.model.GrammarElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public final static String GRAMMAR_HTML_PATH = "file:///android_asset/Grammar/";
    public final static String TENSES_HTML_PATH = "file:///android_asset/Tenses/";

    public final static String APP_LINK = "https://play.google.com/store/apps/details?id=com.dariahaze.learning_english";

    public final static List<GrammarElement> grammarOtherElements;
    public final static List<GrammarElement> grammarTensesElements;
    public final static List<GrammarElement> grammarElements;

    static {
        //TODO WRITE PATH
        grammarOtherElements = new ArrayList<>(Arrays.asList(
                new GrammarElement("Number", GRAMMAR_HTML_PATH + "Number" + ".html"),
                new GrammarElement("Gender", GRAMMAR_HTML_PATH + "Gender" + ".html"),
                new GrammarElement("Capitalization", GRAMMAR_HTML_PATH + "Capitalization" + ".html"),
                new GrammarElement("Possessive", GRAMMAR_HTML_PATH + "Possessive" + ".html"),
                new GrammarElement("Order of Adjectives", GRAMMAR_HTML_PATH + "Order of Adjectives" + ".html"),
                new GrammarElement("Independent and Dependent Clauses", GRAMMAR_HTML_PATH + "Independent and Dependent Clauses" + ".html"),
                new GrammarElement("Shall", GRAMMAR_HTML_PATH + "Shall" + ".html"),
                new GrammarElement("Should", GRAMMAR_HTML_PATH + "Should" + ".html"),
                new GrammarElement("Subjects, Verbs and Objects", GRAMMAR_HTML_PATH + "Subjects, Verbs and Objects" + ".html"),
                new GrammarElement("The imperative", GRAMMAR_HTML_PATH + "Number" + ".html"),
                new GrammarElement("Use Of Little, A Little, Few", GRAMMAR_HTML_PATH + "Use Of Little, A Little, Few" + ".html"),
                new GrammarElement("Use of Shall", GRAMMAR_HTML_PATH + "Use of Shall" + ".html"),
                new GrammarElement("Use of Should", GRAMMAR_HTML_PATH + "Use of Should" + ".html"),
                new GrammarElement("Use Of Unless", GRAMMAR_HTML_PATH + "Use Of Unless" + ".html"),
                new GrammarElement("Used to", GRAMMAR_HTML_PATH + "Used to" + ".html"),
                new GrammarElement("Vocabulary", GRAMMAR_HTML_PATH + "Vocabulary" + ".html"),
                new GrammarElement("To Get", GRAMMAR_HTML_PATH + "To Get" + ".html"),
                new GrammarElement("Punctuation Rules", GRAMMAR_HTML_PATH + "Punctuation Rules" + ".html"),
                new GrammarElement("Homonyms", GRAMMAR_HTML_PATH + "Homonyms" + ".html")
        ));

        GrammarElement pastTenses = new GrammarElement("Past tenses",TENSES_HTML_PATH + "Past tense/");
        GrammarElement presentTenses = new GrammarElement("Present tenses",TENSES_HTML_PATH + "Present tense/");
        GrammarElement futureTenses = new GrammarElement("Future tenses",TENSES_HTML_PATH + "Future tense/");

        pastTenses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Simple past",pastTenses.getPath()+"Simple past.html"),
                new GrammarElement("Past continuous",pastTenses.getPath()+"Past continuous.html"),
                new GrammarElement("Past perfect",pastTenses.getPath()+"Past perfect.html"),
                new GrammarElement("Past perfect continuous",pastTenses.getPath()+"Past perfect continuous.html")
        )));

        presentTenses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Simple present",presentTenses.getPath()+"Simple present.html"),
                new GrammarElement("Present continuous",presentTenses.getPath()+"Present continuous.html"),
                new GrammarElement("Present perfect",presentTenses.getPath()+"Present perfect.html"),
                new GrammarElement("For experience",presentTenses.getPath()+"For experience.html"),
                new GrammarElement("For continuing situation",presentTenses.getPath()+"For continuing situation.html"),
                new GrammarElement("For change",presentTenses.getPath()+"For change.html"),
                new GrammarElement("Present perfect continuous",presentTenses.getPath()+"Present perfect continuous.html")
        )));

        futureTenses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Simple future",futureTenses.getPath()+"Simple future.html"),
                new GrammarElement("Future continuous",futureTenses.getPath()+"Future continuous.html"),
                new GrammarElement("Future perfect",futureTenses.getPath()+"Future perfect.html"),
                new GrammarElement("Future perfect continuous",futureTenses.getPath()+"Future perfect continuous.html")
        )));

        grammarTensesElements = new ArrayList<>(Arrays.asList(
                pastTenses, presentTenses, futureTenses
        ));

        GrammarElement articles = new GrammarElement("Articles", GRAMMAR_HTML_PATH + "Articles/");
        GrammarElement idioms = new GrammarElement("Idioms", GRAMMAR_HTML_PATH + "Idioms/");
        GrammarElement phrases = new GrammarElement("Phrases", GRAMMAR_HTML_PATH + "Phrases/");
        GrammarElement plurals = new GrammarElement("Plurals", GRAMMAR_HTML_PATH + "Plurals/");
        GrammarElement infinitives = new GrammarElement("Infinitives", GRAMMAR_HTML_PATH + "Infinitives/");
        GrammarElement partsOfSpeech = new GrammarElement("Parts Of Speech", GRAMMAR_HTML_PATH + "PartsOfSpeech/");
        GrammarElement conditionals = new GrammarElement("Conditionals", GRAMMAR_HTML_PATH + "Conditionals/");
        GrammarElement activeAndPassiveVoice = new GrammarElement("Active And Passive Voice", GRAMMAR_HTML_PATH + "Active And Passive Voice/");
        GrammarElement auxiliaryVerbs = new GrammarElement("AuxiliaryVerbs", GRAMMAR_HTML_PATH + "Auxiliary Verbs/");
        GrammarElement distributives = new GrammarElement("Distributives", GRAMMAR_HTML_PATH + "Distributives/");
        GrammarElement determiners = new GrammarElement("Determiners", GRAMMAR_HTML_PATH + "Determiners/");
        GrammarElement quantifiers = new GrammarElement("Quantifiers", GRAMMAR_HTML_PATH + "Quantifiers/");
        GrammarElement gerundAndPresentParticles = new GrammarElement("Gerund And Present Participles", GRAMMAR_HTML_PATH + "Gerund And Present Participles/");
        GrammarElement questionTags = new GrammarElement("Question Tags", GRAMMAR_HTML_PATH + "Question Tags/");
        GrammarElement relativeClauses = new GrammarElement("Relative Clauses", GRAMMAR_HTML_PATH + "Relative Clauses/");


        articles.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction Of Articles", articles.getPath()+"Introduction.html"),
                new GrammarElement("Usage Of Articles", articles.getPath()+"Usage.html")
        )));

        idioms.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction Of Idioms", idioms.getPath()+"Introduction.html"),
                new GrammarElement("Examples Of Idioms", idioms.getPath()+"Examples.html")
        )));

        phrases.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction Of Phrases", phrases.getPath()+"Introduction.html"),
                new GrammarElement("Types Of Phrases", phrases.getPath()+"Types Of Phrases.html"),
                new GrammarElement("Types Of Phrases Cont'd", phrases.getPath()+"Types Of Phrases Cont'd.html")
        )));

        plurals.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Plurals", plurals.getPath()+"Plurals.html")
        )));

        infinitives.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Infinitives Part 1", infinitives.getPath()+"Infinitives Part 1.html"),
                new GrammarElement("Infinitives Part 2", infinitives.getPath()+"Infinitives Part 2.html")
        )));

        partsOfSpeech.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Parts Of Speech", partsOfSpeech.getPath()+"Parts_Of_Speech.html"),
                new GrammarElement("Adjectives", partsOfSpeech.getPath()+"Adjectives.html"),
                new GrammarElement("Adverb", partsOfSpeech.getPath()+"Adverb.html"),
                new GrammarElement("Conjunction", partsOfSpeech.getPath()+"Conjunction.html"),
                new GrammarElement("Interjections", partsOfSpeech.getPath()+"Interjections.html"),
                new GrammarElement("Noun", partsOfSpeech.getPath()+"Noun.html"),
                new GrammarElement("Prepositions", partsOfSpeech.getPath()+"Prepositions.html"),
                new GrammarElement("Pronoun", partsOfSpeech.getPath()+"Pronoun.html"),
                new GrammarElement("Verb", partsOfSpeech.getPath()+"Verb.html")
        )));

        conditionals.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction Of Conditional", conditionals.getPath()+"Introduction.html"),
                new GrammarElement("The First Conditional", conditionals.getPath()+"The First Conditional.html"),
                new GrammarElement("The Second Conditional", conditionals.getPath()+"The Second Conditional.html"),
                new GrammarElement("The Third Conditional", conditionals.getPath()+"The Third Conditional.html"),
                new GrammarElement("The Zero Conditional", conditionals.getPath()+"The Zero Conditional.html")
        )));

        activeAndPassiveVoice.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Active And Passive Voice", activeAndPassiveVoice.getPath()+"Introduction.html"),
                new GrammarElement("Rules Of Active And Passive Voice", activeAndPassiveVoice.getPath()+"Rules.html"),
                new GrammarElement("Examples Of Active And Passive Voice", activeAndPassiveVoice.getPath()+"Examples.html")
        )));

        auxiliaryVerbs.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction", auxiliaryVerbs.getPath()+"Introduction.html"),
                new GrammarElement("Usage", auxiliaryVerbs.getPath()+"Usage.html"),
                new GrammarElement("Statements And Questions", auxiliaryVerbs.getPath()+"Statements And Questions.html")
        )));

        auxiliaryVerbs.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction", auxiliaryVerbs.getPath()+"Introduction.html"),
                new GrammarElement("Usage", auxiliaryVerbs.getPath()+"Usage.html"),
                new GrammarElement("Statements And Questions", auxiliaryVerbs.getPath()+"Statements And Questions.html")
        )));

        distributives.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("All, Both, Half", distributives.getPath()+"All,Both,Half.html"),
                new GrammarElement("Each, Either, E very, Neither", distributives.getPath()+"Each,Either,Every,Neither.html")
        )));

        determiners.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction", determiners.getPath()+"Introduction.html"),
                new GrammarElement("Types", determiners.getPath()+"Types.html"),
                new GrammarElement("Defining Words", determiners.getPath()+"Defining_Words.html"),
                new GrammarElement("Definite Articles", determiners.getPath()+"Definite_Articles.html"),
                new GrammarElement("Exception To Using The Definite Articles", determiners.getPath()+"Exception_To_Using_The_Definite_Articles.html"),
                new GrammarElement("Such What Rather Quite", determiners.getPath()+"Such_What_Rather_Quite.html"),
                new GrammarElement("The Demonstratives", determiners.getPath()+"The_Demonstratives.html"),
                new GrammarElement("The Demonstratives", determiners.getPath()+"The_Demonstratives.html"),
                new GrammarElement("The Which And Whose", determiners.getPath()+"Which_And_Whose.html"),
                new GrammarElement("Which What Whose", determiners.getPath()+"Which_What_Whose.html")
        )));

        quantifiers.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Quantifiers Introduction", quantifiers.getPath()+"Quantifiers Introduction.html"),
                new GrammarElement("Quantifiers That Express Attitude", quantifiers.getPath()+"Quantifiers that express attitude.html"),
                new GrammarElement("Comparative Quantifiers", quantifiers.getPath()+"Comparative Quantifiers.html"),
                new GrammarElement("Graded Quantifiers", quantifiers.getPath()+"Graded_Quantifiers.html"),
                new GrammarElement("Quantifiers With Countable And Uncountable Nouns", quantifiers.getPath()+"Quantifiers_with_countable_and _uncountable_nouns.html"),
                new GrammarElement("The Quantifiers Some And Any", quantifiers.getPath()+"The_Quantifiers_Some_and_Any.html"),
                new GrammarElement("The Quantifiers A Few, few, A Little, Little", quantifiers.getPath()+"The_Quantifiers_Few_Little.html"),
                new GrammarElement("The Quantifiers Compound Nouns Made With SOME, ANY, NO", quantifiers.getPath()+"The_Quantifiers_Compound_nouns_made_with_SOME,_ANY_and_NO.html")
        )));

        gerundAndPresentParticles.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Gerund Or Infinitive", gerundAndPresentParticles.getPath()+"Gerund or Infinitive.html"),
                new GrammarElement("The Gerunds", gerundAndPresentParticles.getPath()+"The Gerunds.html"),
                new GrammarElement("The Present Participle", gerundAndPresentParticles.getPath()+"The Present Participle.html"),
                new GrammarElement("The Gerund And The Present Participle", gerundAndPresentParticles.getPath()+"The Gerund and the Present Participle.html"),
                new GrammarElement("Verbs Followed By The Gerund", gerundAndPresentParticles.getPath()+"Verbs Followed By The Gerund.html")
        )));

        questionTags.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction Of Question Tags", questionTags.getPath()+"Introduction.html"),
                new GrammarElement("Rules Of Question Tags", questionTags.getPath()+"Rules.html"),
                new GrammarElement("Examples Of Question Tags", questionTags.getPath()+"Examples.html")
        )));

        relativeClauses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Introduction", relativeClauses.getPath()+"An Introduction.html"),
                new GrammarElement("Defining Relative Clauses", relativeClauses.getPath()+"Defining Relative Clauses.html"),
                new GrammarElement("Non-Defining Relative Clauses", relativeClauses.getPath()+"Non-Defining Relative Clauses.html"),
                new GrammarElement("Where To Put The Preposition In A Relative Clause", relativeClauses.getPath()+"Where to put the preposition in a relative clause.html")
        )));

        grammarElements = new ArrayList<>(Arrays.asList(articles, idioms, phrases, plurals,
                infinitives, partsOfSpeech, conditionals, activeAndPassiveVoice, auxiliaryVerbs,
                distributives, determiners, quantifiers, gerundAndPresentParticles, quantifiers,
                relativeClauses));


    }


}
