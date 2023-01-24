package com.example.android57;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Game implements Serializable {
    private String name;
    private GregorianCalendar date;
    private String result;
    private ArrayList<Move> moves;


    public Game(String name, GregorianCalendar date, String result,ArrayList<Move> moves){
        this.name=name;
        this.date=date;
        this.result=result;
        this.moves=moves;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setDate(GregorianCalendar date){
        this.date=date;
    }

    public void setResult(String result){
        this.result=result;
    }

    public void setMoves(ArrayList<Move> moves){
        this.moves=moves;
    }

    public String getName(){
        return this.name;
    }

    public GregorianCalendar getDate(){
        return this.date;
    }

    public String getResult(){
        return this.result;
    }

    public ArrayList getMoves(){
        return this.moves;
    }


}
