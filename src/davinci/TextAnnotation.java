/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davinci;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Subramanian
 */
public class TextAnnotation {
    private ArrayList<Character> textInputKey;
    
    public TextAnnotation(){
        textInputKey = new ArrayList<Character>();
    }
    
    public void setChar(Character c){
        textInputKey.add(c);
    }
    
    public ArrayList<Character> getChar(){
        return textInputKey;
    }
    
    public void deleteChar(){
        textInputKey.remove(textInputKey.size()-1);
    }
}
