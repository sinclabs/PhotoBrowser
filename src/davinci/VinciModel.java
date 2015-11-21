/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davinci;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Subramanian
 */
public class VinciModel implements DaVinciModel {

    private BufferedImage photo;
    private ArrayList<ChangeListener> listenerList = new ArrayList<ChangeListener>();
    private AnnotationLibrary annLib = new AnnotationLibrary();
    private TextAnnotation textAnn = new TextAnnotation();
    
    Boolean isPhoto = false;
    Boolean isFlipped = false;
    Boolean isAnnotated = false;
    
    Boolean textInput = false;
    Boolean textInputKeyStart = false;
    Boolean txtAnnotation = false;
    private Point textInputPt = new Point();
    
    
    public void setImage(String filePath) {
        try {                
          photo = ImageIO.read(new File(filePath));
          isPhoto = true;
       } 
       catch (IOException ex) {
           
       }
    }

    
    public void setKeyListener(ChangeListener k) {
        listenerList.add(k);
    }

    
    public void setMouseListener(ChangeListener m) {
        listenerList.add(m);
    }
    
    public void setMouseMotionListener(ChangeListener m) {
        listenerList.add(m);
    }

    public void removeChangeListener(ChangeListener c) {
        listenerList.remove(c);
    }
    
    public void addChangeListener(ChangeListener c) {
        listenerList.add(c);
    }
    
    public void includePoints(Point P) {
        annLib.includePoints(P);
    }

    
    public void stopPoints() {
        annLib.stopPoints();
    }
    
    public VinciModel(String fileName){
        this.setImage(fileName);
        System.out.println("Photo is read");
    }
    
    public AnnotationLibrary getAnnLib() {
        return annLib;
    }

    @Override
    public boolean isFlipped() {
        return isFlipped;
    }

    @Override
    public void flipImage(Boolean state) {
        isFlipped = state;
    }

    @Override
    public BufferedImage getImage() {
        return photo;
    }

    @Override
    public boolean isAnnotated() {
        return isAnnotated;
    }

    @Override
    public void annotateImage() {
        if(!isAnnotated)
            isAnnotated = true;
    }

    @Override
    public void includeChar(Character c) {
        textAnn.setChar(c);
    }

    @Override
    public TextAnnotation getChar() {
        return textAnn;
    }

    @Override
    public void deleteChar() {
        textAnn.deleteChar();
    }

    @Override
    public void setTextStart(Boolean state) {
        textInputKeyStart = true;
    }

    @Override
    public Boolean getTextStart() {
        return textInputKeyStart;
    }
    
}
