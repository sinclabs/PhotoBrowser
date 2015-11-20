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
public class SketchPath {
    private ArrayList<Point> path;
    
    public SketchPath(){
        path = new ArrayList<Point>();
    }
    
    public void setPath(Point P){
        path.add(P);
    }
    
    public ArrayList<Point> getPath(){
        return path;
    }
    
    public void erasePath(){
        path.add(new Point(-1,-1));
    }
}
