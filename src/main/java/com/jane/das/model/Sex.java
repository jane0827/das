package com.jane.das.model;

import lombok.Getter;

@Getter
public enum Sex {

    Male(1,"男"),
    Female(2,"女");

    private int value;
    private String text;

    private Sex(int value, String text){
        this.value = value;
        this.text = text;
    }

    private static Sex fromText(String text){
        for(Sex sex : Sex.values()){
            if(sex.getText().equals(text)){
                return sex;
            }
        }
        return null;
    }
}
