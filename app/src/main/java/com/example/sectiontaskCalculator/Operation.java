package com.example.sectiontaskCalculator;

public enum Operation {
    ADD('+'), SUB('-'), MUL('*'), DIV('/');


    private final char sign;
    Operation(char sign){
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }
}
