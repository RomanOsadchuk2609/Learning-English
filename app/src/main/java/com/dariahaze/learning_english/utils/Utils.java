package com.dariahaze.learning_english.utils;

import com.dariahaze.learning_english.model.GrammarElement;
import com.dariahaze.learning_english.model.VideoLesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Utils {
    public final static String GRAMMAR_HTML_PATH = "file:///android_asset/Grammar/";
    public final static String OTHER_HTML_PATH = "file:///android_asset/Other/";
    public final static String TENSES_HTML_PATH = "file:///android_asset/Tenses/";
    public final static String PRACTICE_TESTS_PATH = "Practice/";
    public final static int AMOUNT_OF_TOPICS = 61;
    public final static int AMOUNT_OF_TESTS = 27;

    public final static String APP_LINK = "https://play.google.com/store/apps/details?id=com.dariahaze.learning_english";

    public final static List<GrammarElement> grammarOtherElements;
    public final static List<GrammarElement> grammarTensesElements;
    public final static List<GrammarElement> grammarElements;
    public final static List<VideoLesson> videoLessons;
    public final static List<String> practiceTests;

    public static String getFormattedUserKey(String email){
        return email.replaceAll("\\."," ");
    }
    public static String generateStringId(){
        return UUID.randomUUID().toString();
    }

    static {
        //TODO: WRITE NEW PATH
        grammarOtherElements = new ArrayList<>(Arrays.asList(
                new GrammarElement("Ability", OTHER_HTML_PATH + "Ability" + ".html"),
                new GrammarElement("Admitting Mistakes", OTHER_HTML_PATH + "Admitting Mistakes" + ".html"),
                new GrammarElement("Agreeing and Disagreeing", OTHER_HTML_PATH + "Agreeing and Disagreeing" + ".html"),
                new GrammarElement("Asking and Giving Directions in English", OTHER_HTML_PATH + "Asking and Giving Directions in English" + ".html"),
                new GrammarElement("Asking and Giving Instructions", OTHER_HTML_PATH + "Asking and Giving Instructions" + ".html"),
                new GrammarElement("Asking and Giving Permission", OTHER_HTML_PATH + "Asking and Giving Permission" + ".html"),
                new GrammarElement("Asking for Information", OTHER_HTML_PATH + "Asking for Information" + ".html"),
                new GrammarElement("Asking for Opinions", OTHER_HTML_PATH + "Asking for Opinions" + ".html"),
                new GrammarElement("Blaming and Accusing", OTHER_HTML_PATH + "Blaming and Accusing" + ".html"),
                new GrammarElement("Certainty and Uncertainty", OTHER_HTML_PATH + "Certainty and Uncertainty" + ".html"),

                new GrammarElement("Checking for Understanding and Asking for Clarification", OTHER_HTML_PATH + "Checking for Understanding and Asking for Clarification" + ".html"),
                new GrammarElement("Congratulations and Best Wishes", OTHER_HTML_PATH + "Congratulations and Best Wishes" + ".html"),
                new GrammarElement("Demanding Explanations", OTHER_HTML_PATH + "Demanding Explanations" + ".html"),
                new GrammarElement("Do you speak English?", OTHER_HTML_PATH + "Do you speak English" + ".html"),
                new GrammarElement("Expressing Cause and Effect", OTHER_HTML_PATH + "Expressing Cause and Effect" + ".html"),
                new GrammarElement("Expressing Disappointment", OTHER_HTML_PATH + "Expressing Disappointment" + ".html"),
                new GrammarElement("Expressing Indifference", OTHER_HTML_PATH + "Expressing Indifference" + ".html"),
                new GrammarElement("Expressing Obligation", OTHER_HTML_PATH + "Expressing Obligation" + ".html"),
                new GrammarElement("Expressing Preferences", OTHER_HTML_PATH + "Expressing Preferences" + ".html"),
                new GrammarElement("Expressing Prohibition", OTHER_HTML_PATH + "Expressing Prohibition" + ".html"),

                new GrammarElement("Expressing Regret", OTHER_HTML_PATH + "Expressing Regret" + ".html"),
                new GrammarElement("Expressing Shock", OTHER_HTML_PATH + "Expressing Shock" + ".html"),
                new GrammarElement("Expressing Sympathy", OTHER_HTML_PATH + "Expressing Sympathy" + ".html"),
                new GrammarElement("Fear and Anxiety", OTHER_HTML_PATH + "Fear and Anxiety" + ".html"),
                new GrammarElement("Giving Bad News", OTHER_HTML_PATH + "Giving Bad News" + ".html"),
                new GrammarElement("Giving Compliments", OTHER_HTML_PATH + "Giving Compliments" + ".html"),
                new GrammarElement("Giving Good News", OTHER_HTML_PATH + "Giving Good News" + ".html"),
                new GrammarElement("Giving Opinions", OTHER_HTML_PATH + "Giving Opinions" + ".html"),
                new GrammarElement("Greeting", OTHER_HTML_PATH + "Greeting" + ".html"),
                new GrammarElement("Guessing", OTHER_HTML_PATH + "Guessing" + ".html"),

                new GrammarElement("Hope and Desires", OTHER_HTML_PATH + "Hope and Desires" + ".html"),
                new GrammarElement("Interrupting People", OTHER_HTML_PATH + "Interrupting People" + ".html"),
                new GrammarElement("Introduce Yourself and Others", OTHER_HTML_PATH + "introduce yourself and others" + ".html"),
                new GrammarElement("Likes and Dislikes", OTHER_HTML_PATH + "Likes and Dislikes" + ".html"),
                new GrammarElement("Making an Appointment", OTHER_HTML_PATH + "Making an Appointment" + ".html"),
                new GrammarElement("Making and Answering A Phone Call", OTHER_HTML_PATH + "Making and Answering A Phone Call" + ".html"),
                new GrammarElement("Making Apologies", OTHER_HTML_PATH + "Making Apologies" + ".html"),
                new GrammarElement("Making Complaints", OTHER_HTML_PATH + "Making Complaints" + ".html"),
                new GrammarElement("Making Friends", OTHER_HTML_PATH + "Making Friends" + ".html"),
                new GrammarElement("Making Invitations", OTHER_HTML_PATH + "Making Invitations" + ".html"),

                new GrammarElement("Making Offers", OTHER_HTML_PATH + "Making Offers" + ".html"),
                new GrammarElement("Making Promises", OTHER_HTML_PATH + "Making Promises" + ".html"),
                new GrammarElement("Making Requests", OTHER_HTML_PATH + "Making Requests" + ".html"),
                new GrammarElement("Making Suggestions and Giving Advice", OTHER_HTML_PATH + "Making Suggestions and Giving Advice" + ".html"),
                new GrammarElement("Saying Goodbye", OTHER_HTML_PATH + "Saying Goodbye" + ".html"),
                new GrammarElement("Talking About Feelings", OTHER_HTML_PATH + "Talking About Feelings" + ".html"),
                new GrammarElement("Talking About Health and Illness", OTHER_HTML_PATH + "Talking About Health and Illness" + ".html"),
                new GrammarElement("Talking About Jobs and Occupations", OTHER_HTML_PATH + "Talking About Jobs and Occupations" + ".html"),
                new GrammarElement("Talking About Time", OTHER_HTML_PATH + "Talking About Time" + ".html"),
                new GrammarElement("Thank You", OTHER_HTML_PATH + "Thank You" + ".html")
        ));

        GrammarElement pastTenses = new GrammarElement("Past tenses",TENSES_HTML_PATH + "Past/");
        GrammarElement presentTenses = new GrammarElement("Present tenses",TENSES_HTML_PATH + "Present/");
        GrammarElement futureTenses = new GrammarElement("Future tenses",TENSES_HTML_PATH + "Future/");

        pastTenses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Past",pastTenses.getPath()+"past.html"),
                new GrammarElement("Simple past",pastTenses.getPath()+"Simple past tense.html"),
                new GrammarElement("Past continuous",pastTenses.getPath()+"Past continuous tense.html"),
                new GrammarElement("Past perfect",pastTenses.getPath()+"Past perfect tense.html"),
                new GrammarElement("Past perfect continuous",pastTenses.getPath()+"Past perfect continuous tense.html")
        )));

        presentTenses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Present",presentTenses.getPath()+"present.html"),
                new GrammarElement("Simple present",presentTenses.getPath()+"Simple present tense.html"),
                new GrammarElement("Present continuous",presentTenses.getPath()+"Present continuous.html"),
                new GrammarElement("Present perfect",presentTenses.getPath()+"Present perfect.html"),
                new GrammarElement("Present perfect continuous",presentTenses.getPath()+"Present perfect continuous.html")
        )));

        futureTenses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Future",futureTenses.getPath()+"future.html"),
                new GrammarElement("Simple future",futureTenses.getPath()+"Simple future tense.html"),
                new GrammarElement("Future continuous",futureTenses.getPath()+"Future continuous.html"),
                new GrammarElement("Future perfect",futureTenses.getPath()+"Future perfect.html"),
                new GrammarElement("Future perfect continuous",futureTenses.getPath()+"Future perfect continuous.html"),
                new GrammarElement("Immediate future",futureTenses.getPath()+"Immediate future.html"),
                new GrammarElement("Future obligation",futureTenses.getPath()+"Future obligation.html"),
                new GrammarElement("Future with going",futureTenses.getPath()+"Future with going.html"),
                new GrammarElement("Present continuous for future arrangements",futureTenses.getPath()+"Present continuous for future arrangements.html"),
                new GrammarElement("Simple present for future events",futureTenses.getPath()+"Simple present for future events.html")
        )));

        grammarTensesElements = new ArrayList<>(Arrays.asList(
                pastTenses, presentTenses, futureTenses
        ));

        GrammarElement adjectives = new GrammarElement("Adjectives", GRAMMAR_HTML_PATH + "ADJECTIVES/");
        GrammarElement adverbs = new GrammarElement("Adverbs", GRAMMAR_HTML_PATH + "ADVERBS/");
        GrammarElement determiners = new GrammarElement("Determiners", GRAMMAR_HTML_PATH + "DETERMINERS/");
        GrammarElement nouns = new GrammarElement("Nouns", GRAMMAR_HTML_PATH + "NOUNS/");
        GrammarElement punctuation = new GrammarElement("Punctuation", GRAMMAR_HTML_PATH + "PUNCTUATION/");
        GrammarElement relativeClauses = new GrammarElement("Relative Clauses", GRAMMAR_HTML_PATH + "RELATIVE CLAUSES/");
        GrammarElement speech = new GrammarElement("Speech", GRAMMAR_HTML_PATH + "SPEECH/");
        GrammarElement verbsConditional = new GrammarElement("Verbs. Conditional", GRAMMAR_HTML_PATH + "VERBS/CONDITIONAL/");
        GrammarElement verbsIngForms = new GrammarElement("Verbs. The -ing Forms", GRAMMAR_HTML_PATH + "VERBS/THE -ING FORMS/");
        GrammarElement verbsInfinitives = new GrammarElement("Verbs. Infinitives", GRAMMAR_HTML_PATH + "VERBS/INFINITIVE/");
        GrammarElement verbsPassiveVoice = new GrammarElement("Verbs. Passive Voice", GRAMMAR_HTML_PATH + "VERBS/PASSIVE VOICE/");

        nouns.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Capitalization rules", nouns.getPath()+"Capitalization rules.html"),
                new GrammarElement("Noun gender", nouns.getPath()+"Noun gender.html"),
                new GrammarElement("Singular and plural nouns", nouns.getPath()+"Singular and plural nouns.html"),
                new GrammarElement("Countable and uncountable nouns", nouns.getPath()+"Countable and uncountable nouns.html"),
                new GrammarElement("Pronouns", nouns.getPath()+"Pronouns.html"),
                new GrammarElement("Indefinite pronouns", nouns.getPath()+"Indefinite pronouns.html"),
                new GrammarElement("Compound nouns", nouns.getPath()+"Compound nouns.html"),
                new GrammarElement("Nationalities", nouns.getPath()+"Nationalities.html"),
                new GrammarElement("Forming the possessive", nouns.getPath()+"Forming the possessive.html")
        )));

        adjectives.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Functions of Adjectives", adjectives.getPath()+"Functions of Adjectives.html"),
                new GrammarElement("Using adjectives in English", adjectives.getPath()+"Using adjectives in English.html"),
                new GrammarElement("Ordering multiple adjectives", adjectives.getPath()+"Ordering multiple adjectives.html"),
                new GrammarElement("The comparative and the superlative", adjectives.getPath()+"The comparative and the superlative.html"),
                new GrammarElement("Comparing attributes", adjectives.getPath()+"Comparing attributes.html"),
                new GrammarElement("Adjectives comparing equal quantities", adjectives.getPath()+"Adjectives comparing equal quantities.html"),
                new GrammarElement("Adjectives comparing unequal quantities", adjectives.getPath()+"Adjectives comparing unequal quantities.html")
        )));

        adverbs.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Using adverbs in English", adverbs.getPath()+"Using adverbs in English.html"),
                new GrammarElement("Forming adverbs from adjectives", adverbs.getPath()+"Forming adverbs from adjectives.html"),
                new GrammarElement("Comparative and superlative adverbs", adverbs.getPath()+"Comparative and superlative adverbs.html"),
                new GrammarElement("Adverbs of place", adverbs.getPath()+"Adverbs of place.html"),
                new GrammarElement("Adverbs of time", adverbs.getPath()+"Adverbs of time.html"),
                new GrammarElement("Adverbs of manner", adverbs.getPath()+"Adverbs of manner.html"),
                new GrammarElement("Adverbs of degree", adverbs.getPath()+"Adverbs of degree.html"),
                new GrammarElement("Adverbs of certainty", adverbs.getPath()+"Adverbs of certainty.html"),
                new GrammarElement("Viewpoint and commenting adverbs", adverbs.getPath()+"Viewpoint and commenting adverbs.html"),
                new GrammarElement("Relative adverbs", adverbs.getPath()+"Relative adverbs.html"),
                new GrammarElement("Interrogative adverbs", adverbs.getPath()+"Interrogative adverbs.html")
        )));

        determiners.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("The definite article", determiners.getPath()+"The definite article.html"),
                new GrammarElement("Indefinite Articles", determiners.getPath()+"Indefinite Articles.html"),
                new GrammarElement("Demonstratives", determiners.getPath()+"Demonstratives.html"),
                new GrammarElement("Pronouns", determiners.getPath()+"Pronouns.html"),
                new GrammarElement("Quantifiers", determiners.getPath()+"Quantifiers.html"),
                new GrammarElement("Numbers in English", determiners.getPath()+"Numbers in English.html"),
                new GrammarElement("Pick the right quantifier", determiners.getPath()+"Pick the right quantifier.html"),
                new GrammarElement("Expressing opinions about quantity", determiners.getPath()+"Expressing opinions about quantity.html"),
                new GrammarElement("Indefinite and Incomplete Quantities", determiners.getPath()+"Indefinite and Incomplete Quantities.html"),
                new GrammarElement("Graded quantifiers", determiners.getPath()+"Graded quantifiers.html"),
                new GrammarElement("Enough as a quantifier", determiners.getPath()+"Enough as a quantifier.html"),
                new GrammarElement("Distributives", determiners.getPath()+"Distributives.html"),
                new GrammarElement("Using \"each\" and \"every\"", determiners.getPath()+"Using each and every.html"),
                new GrammarElement("Using \"all\" as a distributive", determiners.getPath()+"Using all as a distributive.html"),
                new GrammarElement("Using \"half\" as a distributive", determiners.getPath()+"Using half as a distributive.html"),
                new GrammarElement("Distributives for pairs of objects", determiners.getPath()+"Distributives for pairs of objects.html"),
                new GrammarElement("Determiners of difference", determiners.getPath()+"Determiners of difference.html"),
                new GrammarElement("Pre-determiners", determiners.getPath()+"Pre-determiners.html")
        )));

        speech.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Direct and Indirect Speech", speech.getPath()+"Direct and Indirect Speech.html"),
                new GrammarElement("Tense Changes When Using Reported Speech", speech.getPath()+"Tense Changes When Using Reported Speech.html"),
                new GrammarElement("Changing time and place references", speech.getPath()+"Changing time and place references.html"),
                new GrammarElement("Question Forms and Reported Speech", speech.getPath()+"Question Forms and Reported Speech.html"),
                new GrammarElement("Reporting verbs", speech.getPath()+"Reporting verbs.html"),
                new GrammarElement("Reported Speech part 1", speech.getPath()+"Reported Speech part 1.html"),
                new GrammarElement("Reported Speech part 2", speech.getPath()+"Reported Speech part 2.html")
        )));

        punctuation.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Punctuation", punctuation.getPath()+"Punctuation.html"),
                new GrammarElement("The Period, Full Stop or Poin", punctuation.getPath()+"The Period, Full Stop or Poin.html"),
                new GrammarElement("The Comma", punctuation.getPath()+"The Comma.html"),
                new GrammarElement("The Exclamation Mark", punctuation.getPath()+"The Exclamation Mark.html"),
                new GrammarElement("The Question Mark", punctuation.getPath()+"The Question Mark.html"),
                new GrammarElement("The Colon", punctuation.getPath()+"The Colon.html"),
                new GrammarElement("The Semicolon", punctuation.getPath()+"The Semicolon.html"),
                new GrammarElement("Quotation Marks", punctuation.getPath()+"Quotation Marks.html"),
                new GrammarElement("The Apostrophe", punctuation.getPath()+"The Apostrophe.html"),
                new GrammarElement("Hyphens and Dashes", punctuation.getPath()+"Hyphens and Dashes.html"),
                new GrammarElement("Brackets and Parentheses", punctuation.getPath()+"Brackets and Parentheses.html")
        )));

        relativeClauses.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Relative clauses", relativeClauses.getPath()+"Relative clauses.html"),
                new GrammarElement("Preposition placement in relative clauses", relativeClauses.getPath()+"Preposition placement in relative clauses.html"),
                new GrammarElement("Defining relative clauses", relativeClauses.getPath()+"Defining relative clauses.html"),
                new GrammarElement("Non-defining relative clauses", relativeClauses.getPath()+"Non-defining relative clauses.html")
        )));

        verbsConditional.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Conditional", verbsConditional.getPath()+"Conditional.html"),
                new GrammarElement("The unreal past", verbsConditional.getPath()+"The unreal past.html"),
                new GrammarElement("How to use \"Unless\"", verbsConditional.getPath()+"How to use Unless.html"),
                new GrammarElement("Zero Conditional", verbsConditional.getPath()+"Zero Conditional.html"),
                new GrammarElement("Type 1 Conditional", verbsConditional.getPath()+"Type 1 Conditional.html"),
                new GrammarElement("Type 2 Conditional", verbsConditional.getPath()+"Type 2 Conditional.html"),
                new GrammarElement("Present Continuous Conditional", verbsConditional.getPath()+"Present Continuous Conditional.html"),
                new GrammarElement("Type 3 Conditional", verbsConditional.getPath()+"Type 3 Conditional.html"),
                new GrammarElement("Perfect Continuous Conditional", verbsConditional.getPath()+"Perfect Continuous Conditional.html"),
                new GrammarElement("Mixed Conditional", verbsConditional.getPath()+"Mixed Conditional.html")
        )));

        verbsIngForms.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("The -ing forms", verbsIngForms.getPath()+"The -ing forms.html"),
                new GrammarElement("Present participle", verbsIngForms.getPath()+"Present participle.html"),
                new GrammarElement("Gerund", verbsIngForms.getPath()+"Gerund.html"),
                new GrammarElement("Verbs followed by gerunds", verbsIngForms.getPath()+"Verbs followed by gerunds.html"),
                new GrammarElement("Gerund does not equal infinitive", verbsIngForms.getPath()+"Gerund does not equal infinitive.html"),
                new GrammarElement("Gerund equals infinitive", verbsIngForms.getPath()+"Gerund equals infinitive.html")
        )));

        verbsInfinitives.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Infinitive", verbsInfinitives.getPath()+"Infinitive.html"),
                new GrammarElement("Perfect Infinitive", verbsInfinitives.getPath()+"Perfect Infinitive.html"),
                new GrammarElement("Perfect continuous infinitive", verbsInfinitives.getPath()+"Perfect continuous infinitive.html"),
                new GrammarElement("Continuous infinitive", verbsInfinitives.getPath()+"Continuous infinitive.html"),
                new GrammarElement("Passive infinitive", verbsInfinitives.getPath()+"Passive infinitive.html"),
                new GrammarElement("Verbs followed by infinitives", verbsInfinitives.getPath()+"Verbs followed by infinitives.html")
        )));

        verbsPassiveVoice.setSubElements(new ArrayList<GrammarElement>(Arrays.asList(
                new GrammarElement("Passive voice", verbsPassiveVoice.getPath()+"Passive voice.html"),
                new GrammarElement("Passive voice to active voice", verbsPassiveVoice.getPath()+"Passive voice to active voice.html"),
                new GrammarElement("Alternative ways to form the passive voice", verbsPassiveVoice.getPath()+"Alternative ways to form the passive voice.html")
        )));

        grammarElements = new ArrayList<>(Arrays.asList(nouns, adjectives, adverbs, determiners,
                verbsConditional, verbsIngForms, verbsInfinitives, verbsPassiveVoice, speech,
                punctuation, relativeClauses));


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

        practiceTests = new ArrayList<>(Arrays.asList(
                "Adverbs", "Adjectives Part One", "Adjectives Part Two",
                "Appropriate Order", "Articles", "Correct Words",
                "Correct Form Of Nouns", "Gerunds", "Homophones",
                "Idioms", "If And Unless", "Interjection",
                "Nouns And Verb", "Collective Nouns", "Participles",
                "Choose Best Word Or Phrase", "Plural Forms", "Preposition",
                "Pronouns", "verbs", "General Part 1",
                "General Part 2", "General Part 3", "General Part 4",
                "General Part 5", "Phrasal Verbs Part 1", "Phrasal Verbs Part 2"
        ));

    }




}
