package com.dariahaze.learning_english.utils;

import com.dariahaze.learning_english.model.GrammarElement;
import com.dariahaze.learning_english.model.VideoLesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Utils {
    public final static String GRAMMAR_HTML_PATH = "file:///android_asset/Grammar/";
    public final static String TENSES_HTML_PATH = "file:///android_asset/Tenses/";

    public final static String APP_LINK = "https://play.google.com/store/apps/details?id=com.dariahaze.learning_english";

    public final static List<GrammarElement> grammarOtherElements;
    public final static List<GrammarElement> grammarTensesElements;
    public final static List<GrammarElement> grammarElements;
    public final static List<VideoLesson> videoLessons;

    public static String getFormattedUserKey(String email){
        return email.replaceAll("\\."," ");
    }
    public static String generateStringId(){
        return UUID.randomUUID().toString();
    }

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
                new GrammarElement("Comparative Quantifiers", quantifiers.getPath()+"Comparative quantifiers.html"),
                new GrammarElement("Graded Quantifiers", quantifiers.getPath()+"Graded_Quantifiers.html"),
                new GrammarElement("Quantifiers With Countable And Uncountable Nouns", quantifiers.getPath()+"Quantifiers_with_countable_and_uncountable_nouns.html"),
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


        videoLessons = new ArrayList<>(Arrays.asList(
                new VideoLesson("Learn CONTINUOUS TENSES in English the EASY way","https://www.youtube.com/watch?v=23sIBk6gL4w&feature=youtu.be"),
                new VideoLesson("Basic English – How and when to use DO, DOES, and DID","https://www.youtube.com/watch?v=oDGCCJRZU7I"),
                new VideoLesson("Should you use DO or BE?","https://www.youtube.com/watch?v=cd_o-94r8iE"),
                new VideoLesson("WOULD contractions: I'D, YOU'D, HE'D...","https://www.youtube.com/watch?v=dmozaTka7zs"),
                new VideoLesson("What is an auxiliary verb?","https://www.youtube.com/watch?v=HHt52kFa2ow"),
                new VideoLesson("English Prepositions: IN or ON?","https://www.youtube.com/watch?v=TaPg7CW56Qg"),
                new VideoLesson("8 rules for using 'THE' in English","https://www.youtube.com/watch?v=P4U0uLBATZI"),
                new VideoLesson("3 Quick Grammar Fixes","https://www.youtube.com/watch?v=IoN1ABki6HU"),
                new VideoLesson("Pronouns - SHE, HER, HE, HIS","https://www.youtube.com/watch?v=_IK_0sIsfxg&t=3s"),
                new VideoLesson("When to use 'good' and 'well'","https://www.youtube.com/watch?v=xS1Krim0TUo"),
                new VideoLesson("TOO MUCH, TOO MANY, A LOT OF","https://www.youtube.com/watch?v=gFc6STGTU4w"),
                new VideoLesson("10 common verbs followed by infinitives","https://www.youtube.com/watch?v=szb4FJHakIk"),
                new VideoLesson("Noun, Verb, Adjective, Adverb","https://www.youtube.com/watch?v=CzHotHaXGk0"),
                new VideoLesson("AGO & FROM NOW - Talking about time in English","https://www.youtube.com/watch?v=2ltn4UrOTRk"),
                new VideoLesson("Superlative Adjectives - biggest, best, most beautiful, etc","https://www.youtube.com/watch?v=yFWVbV3fd3I"),
                new VideoLesson("Prepositions - Arrive AT, ON, or IN?","https://www.youtube.com/watch?v=4H7VpBwYPOE"),
                new VideoLesson("6 ways to use the verb 'GO' in english ","https://www.youtube.com/watch?v=gDLX3jqkEhI"),
                new VideoLesson("How to compare: \"as cold as ice\"","https://www.youtube.com/watch?v=4CEmvXbTDNg"),
                new VideoLesson("Basic English Grammar - BE verb","https://www.youtube.com/watch?v=1FByqdpRSVs"),
                new VideoLesson("Present Simple - YES/NO QUESTIONS","https://www.youtube.com/watch?v=zEv1DJuhiWU"),
                new VideoLesson("5 Common Irregular Past Tense Verbs","https://www.youtube.com/watch?v=XAFPfyZ8D1Q"),
                new VideoLesson("THIS, THAT, THESE, THOSE","https://www.youtube.com/watch?v=KxmgDmz6JEU&t=7s"),
                new VideoLesson("How to show possession in English - MY / MINE, HER / SHE / HERS, and more!","https://www.youtube.com/watch?v=TKN8WUa_hOI"),
                new VideoLesson("Future Tense - WILL & GOING TO","https://www.youtube.com/watch?v=SmSKJ-t8lJ8"),
                new VideoLesson("When NOT to use prepositions in English","https://www.youtube.com/watch?v=J0MYHI3R5OE"),
                new VideoLesson("How to spell plural nouns easily","https://www.youtube.com/watch?v=kg7PouRs2Lg"),
                new VideoLesson("Supposed to and going to","https://www.youtube.com/watch?v=FWeTXElJPwc"),
                new VideoLesson("Common English Grammar Errors with Plurals","https://www.youtube.com/watch?v=DmtBX8jDwds"),
                new VideoLesson("6 ways to use WILL","https://www.youtube.com/watch?v=YfztVj5Qwqk"),
                new VideoLesson("How to learn grammar – any grammar!!!","https://www.youtube.com/watch?v=c6Y1UJb2fvc"),
                new VideoLesson("Has, have, have got","https://www.youtube.com/watch?v=A6hF53-vHjk"),
                new VideoLesson("3 ways to use HAVE GOT in English","https://www.youtube.com/watch?v=jXDMl3HVEBY"),
                new VideoLesson("Causative Verbs: Make, Have, Let, Get, Help","https://www.youtube.com/watch?v=ZyfLxDTlsOQ"),
                new VideoLesson("Subject-Verb Agreement with EACH, EVERY, ANY, SOME","https://www.youtube.com/watch?v=dmw0kBuuEy4"),
                new VideoLesson("TO or FOR? Prepositions in English","https://www.youtube.com/watch?v=E3gvjrh5_TY"),
                new VideoLesson("How to use 'too' and 'enough' in English","https://www.youtube.com/watch?v=ethbBja46v4"),
                new VideoLesson("Learn English: ALL or WHOLE?","https://www.youtube.com/watch?v=hJAXF8YryL4"),
                new VideoLesson("Alright or All right?","https://www.youtube.com/watch?v=pOFWLKDk3Hk"),
                new VideoLesson("Farther or Further?","https://www.youtube.com/watch?v=ytGzJbu4mPE"),
                new VideoLesson("Past Simple and Past Perfect - Tenses in English","https://www.youtube.com/watch?v=7Mni3yDpIWo")
        ));

    }




}
