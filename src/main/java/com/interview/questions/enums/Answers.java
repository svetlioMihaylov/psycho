package com.interview.questions.enums;

public enum Answers {

    STRONGLY_DISAGREE(1, "Strongly disagree"),
    DISAGREE (2, "Disagree"),
    NEUTRUAL(3, "Neutral"),
    AGREE(4, "Agree"),
    STRONGLY_SAGREE(5, "Strongly agree");

    private int value;
    private String lable;
    Answers(int value, String lable){
        this.lable = lable;
        this.value = value;
    }


}
