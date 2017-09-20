package com.jane.das.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex {

    Male(1,"男"),
    Female(2,"女");

    private int value;
    private String text;

    private static Sex fromText(String text){
        for(Sex sex : Sex.values()){
            if(sex.getText().equals(text)){
                return sex;
            }
        }
        return null;
    }
}
