/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photobrowser;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Subramanian
 */
public class AnnotationLibrary {
    private ArrayList<SketchPath> paths;
    private SketchPath currentPath;
    
    public AnnotationLibrary(){
        paths = new ArrayList<SketchPath>();
        currentPath = new SketchPath();
    }
    public void includePoints(Point P){
        currentPath.setPath(P);
    }
    public void stopPoints(){
        paths.add(currentPath);
        currentPath.erasePath();
    }
    
    public ArrayList<SketchPath> getPaths(){
        return paths;
    }
}
