/*Bill Shi
CSC 221 Project 3*/

package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;



public class huffman {

    public static void Tree(ArrayList<Object> obj){
        if(obj.size() == 1){                                //base case
            return;
        }

        Collections.sort(obj);                              //sort the arraylist by frequency from small to big
        Object temp = new Object();                         //create temp obj which connect to two smallest nodes in current arraylist

        temp.setRightchild(obj.get(0));
        temp.setLeftchild(obj.get(1));
        temp.setFrequency(obj.get(0).getFrequency()+ obj.get(1).getFrequency());
        temp.setCount(obj.get(0).getCount()+  obj.get(1).getCount());

        obj.remove(0);                               //remove the original two smallest objs and add the new temp into the arraylist
        obj.remove(0);
        obj.add(temp);

        Tree(obj);                                          //recursive method
    }

    public static void toHuff(Object a){                    //method go from the top root to the leaves that keep track the route then return
        if(a.getLeftchild() != null){                       //a huff value to the according character
            a.getLeftchild().setHuff(a.getHuff()+'0');
            toHuff(a.getLeftchild());
        }
        if(a.getRightchild() != null){
            a.getRightchild().setHuff(a.getHuff()+'1');
            toHuff(a.getRightchild());
        }
        return;
    }

    public static void testResult(Object a){                                //testing result using this recursive method
        if (a.getRightchild()==null&&a.getLeftchild()==null){
            if(a.getLetter()=='\n'){
                System.out.println("Character: newLine, huff code: "+a.getHuff());
            }
            else if(a.getLetter()==' '){
                System.out.println("Character: space, huff code: "+a.getHuff());
            }
            else if(a.getLetter()=='\r'){
                System.out.println("Character: carriage return, huff code: "+a.getHuff());
            }
            else {
                System.out.println("Character: " + a.getLetter() + ", huff code: " + a.getHuff());
            }
        }
        if(a.getLeftchild() != null){
            testResult(a.getLeftchild());
        }
        if(a.getRightchild() != null){
            testResult(a.getRightchild());
        }
        return;
    }

    public static double returnBits(Object a){                              //calculate the expected bits per character
        double numBits=0.0;
        if(a.getLeftchild() != null){
           numBits+=returnBits(a.getLeftchild());
        }
        if(a.getRightchild() != null){
            numBits+=returnBits(a.getRightchild());
        }
        if (a.getRightchild()==null&&a.getLeftchild()==null){
            return a.getHuff().length()*a.getFrequency();
        }
        return numBits;
    }

    public static ArrayList<Object> obj;
    public static void main(String[] args) throws IOException {
        String filename;
        String lineContent;
        boolean hasSame=false;
        FileInputStream fileByteStream = null;                          //read file index.txt from outside
        Scanner inFS = new Scanner(System.in);
        System.out.println("Input the file name you want to open: ");   //prompt for input of file name
        filename=inFS.nextLine();
        System.out.println("Opening file "+filename);
        fileByteStream = new FileInputStream(filename);
        Scanner inF = new Scanner(fileByteStream);                      //new scanner for file
        obj=new ArrayList<Object>();

        obj.add(new Object('\n'));                                   //add \n character node first and set its count to 0
        obj.get(0).setCount(0);
        obj.add(new Object('\r'));
        obj.get(1).setCount(0);
        while(inF.hasNext()){                                           //continue to loop before the file ends
            lineContent=inF.nextLine();
            for (int i=0;i<lineContent.length();i++){                   //loop through this current line
                for(int j=0; j<obj.size(); j++) {                       //loop through the current arraylist and check for same character
                    if (obj.get(j).getLetter() == lineContent.charAt(i)) {
                        obj.get(j).countplus();                         //if there is same character add the word count by 1
                        hasSame=true;
                        continue;
                    }
                }
                if(!hasSame) {                                          //if there is not a same character, create a new node with current character
                    obj.add(new Object(lineContent.charAt(i)));
                }
                hasSame=false;                                          //set the hasSame back to false in order to loop
            }
            obj.get(0).countplus();                                     //add count of \n by 1
            obj.get(1).countplus();                                     //add count of \r by 1

        }
        System.out.println("Closing file "+filename);
        fileByteStream.close();

        int totalcount=0;                                               //calculate the frequency by dividing current count with totalcount
        for(int i=0;i<obj.size();i++){
            totalcount+=obj.get(i).getCount();
        }
        System.out.println("Totalcount: "+totalcount);
        for(int i=0;i<obj.size();i++){
            obj.get(i).setFrequency((double)obj.get(i).getCount()/totalcount);  //set the frequency to nodes
        }

        Tree(obj);
        toHuff(obj.get(0));
        testResult(obj.get(0));
        System.out.println("the expected number of bits per character: "+ returnBits(obj.get(0)));

    }
}
