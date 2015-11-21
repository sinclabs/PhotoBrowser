package davinci;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.event.ChangeListener;



public interface DaVinciModel {
    public boolean isFlipped();
    public void flipImage(Boolean state);
    public boolean isAnnotated();
    public void annotateImage();
    public void setImage(String filePath);
    public BufferedImage getImage();
    public void setKeyListener(ChangeListener k);
    public void setMouseListener(ChangeListener M);
    public void setMouseMotionListener(ChangeListener M);
    public void removeChangeListener(ChangeListener c);
    public void addChangeListener(ChangeListener c); 
    public void includePoints(Point P);
    public void stopPoints();
    public void includeChar(Character c);
    public TextAnnotation getChar();
    public void deleteChar();
    public AnnotationLibrary getAnnLib();
    public void setTextStart(Boolean state);
    public Boolean getTextStart();
}
