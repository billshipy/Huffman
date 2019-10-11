package com.company;


public class Object implements Comparable<Object>{
    private char letter;
    private int count;
    private double frequency;
    private Object leftchild;
    private Object rightchild;
    private String huff;

    public Object(){
        letter='\0';
        count=0;
        frequency=0;
        leftchild=null;
        rightchild=null;
        huff="";
    }

    public Object(char a){
        letter=a;
        count=1;
        frequency=0;
        leftchild=null;
        rightchild=null;
        huff="";
    }

    public char getLetter(){
        return letter;
    }

    public double getFrequency(){
        return frequency;
    }

    public int getCount(){
        return count;
    }

    public Object getLeftchild() {
        return leftchild;
    }

    public Object getRightchild() {
        return rightchild;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public void setLeftchild(Object C) {
        this.leftchild = C;
    }

    public void setRightchild(Object D) {
        this.rightchild = D;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setHuff(String huff) {
        this.huff = huff;
    }

    public String getHuff() {
        return huff;
    }

    public void countplus(){
        this.count++;
    }

    public int compareTo(Object a){
        if(this.count < a.count){
            return -1;
        }
        else if (this.count == a.count){
            return 0;
        }else{
            return 1;
        }
    }



}
