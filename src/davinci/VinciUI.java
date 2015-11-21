/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davinci;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Subramanian
 */
public class VinciUI extends DaVinciUI implements MouseListener, MouseMotionListener, KeyListener {
    
    Boolean textInput = false;
    Boolean txtAnnotation = false;
    Boolean isPhoto = true;
    private Point textInputPt = new Point();
    
    public VinciUI(){
        
    }
    
    public void installUI(JComponent c) {
        ((DaVinci) c).addMouseListener(this);
        ((DaVinci) c).addMouseMotionListener(this);
        ((DaVinci) c).addKeyListener(this);
        ((DaVinci) c).setFocusable(true);
    }

    public void uninstallUI(JComponent c) {
        ((DaVinci) c).setFocusable(false);
        ((DaVinci) c).removeKeyListener(this);
        ((DaVinci) c).removeMouseMotionListener(this);
        ((DaVinci) c).removeMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DaVinciModel m; 
        m = ((DaVinci)e.getComponent()).getModel();
        if(e.getClickCount() == 2){
            if(!m.isFlipped()){
                m.flipImage(true);
                ((DaVinci)e.getComponent()).modelChanged();
            }
            else{
                m.flipImage(false);
                ((DaVinci)e.getComponent()).modelChanged();
            }
        }
        
        else if(e.getClickCount() == 1){
            if(m.isFlipped()){
                if(textInput)
                    textInput = false;
                else{
                    textInput = true;
                    textInputPt = e.getPoint(); 
                }
                ((DaVinci) e.getComponent()).requestFocus();
                ((DaVinci)e.getComponent()).modelChanged();
            }
        }
        
        if (((DaVinci) e.getComponent()).getModel().isAnnotated() && ((DaVinci) e.getComponent()).getModel().isFlipped())
            ((DaVinci) e.getComponent()).getModel().includePoints(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        DaVinciModel m; 
        m = ((DaVinci)e.getComponent()).getModel();
        if(m.isFlipped() && m.isAnnotated()){
            m.stopPoints();
            ((DaVinci)e.getComponent()).modelChanged();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(((DaVinci) e.getComponent()).getModel().isFlipped()){
            ((DaVinci) e.getComponent()).getModel().includePoints(e.getPoint());
            ((DaVinci) e.getComponent()).getModel().annotateImage();
            ((DaVinci) e.getComponent()).modelChanged();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        DaVinciModel m; 
        m = ((DaVinci)e.getComponent()).getModel();
        if((int)e.getKeyChar() == 8)
            m.deleteChar();
        else if((int)e.getKeyChar() == 27)
            textInput = false;
        else{
            m.includeChar(e.getKeyChar());
            m.setTextStart(true);
        }
        ((DaVinci) e.getComponent()).modelChanged();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    
    public static VinciUI createUI(JComponent c) {
        System.out.println("here");
        return new VinciUI();
        
    }
    
    public void paint(Graphics g, JComponent c){
        System.out.println("Paint method");
        DaVinci comp = (DaVinci) c;
        DaVinciModel model = comp.getModel();
        if(isPhoto){
            if(!model.isFlipped()){
                g.drawImage(model.getImage(), 0, 0, comp.getWidth(), comp.getHeight(), null);         
            }
            else{
                g.setColor(Color.white);
                g.fillRect(0, 0, comp.getWidth(), comp.getHeight());
                if(model.isAnnotated()){
                    ArrayList<SketchPath> annList;
                    AnnotationLibrary annLib = model.getAnnLib();
                    annList = model.getAnnLib().getPaths();
                    int j=0;
                    while(j < annList.size()){
                        ArrayList<Point> pth = annList.get(j).getPath();
                        int i = 0;
                        while(i < pth.size()){
                            g.setColor(Color.red);
                            if(i!=0)
                                if((pth.get(i).x != -1 && pth.get(i).y != -1) && (pth.get(i-1).x != -1 && pth.get(i-1).y != -1))
                                    g.drawLine(pth.get(i-1).x, pth.get(i-1).y, pth.get(i).x, pth.get(i).y);
                            else
                                g.drawLine(pth.get(i).x, pth.get(i).y, pth.get(i).x, pth.get(i).y);
                            i++;
                        }
                        j++;
                    }
                }
                if(model.getTextStart() && !textInput){
                    g.setColor(Color.black);
                    char[] textInputKeyChars = new char[model.getChar().getChar().size()];
                    for(int i = 0; i<model.getChar().getChar().size(); i++){
                        textInputKeyChars[i] = model.getChar().getChar().get(i);
                    }
                    g.drawChars(textInputKeyChars, 0, textInputKeyChars.length, 100, 100);
                }
                if(textInput){
                    System.out.println("a");
                    g.setColor(Color.black);
                    g.drawLine(textInputPt.x, textInputPt.y, textInputPt.x, textInputPt.y-10);
                    if(model.getTextStart()){
                        char[] textInputKeyChars = new char[model.getChar().getChar().size()];
                        for(int i = 0; i<model.getChar().getChar().size(); i++){
                            textInputKeyChars[i] = model.getChar().getChar().get(i);
                        }
                        g.drawChars(textInputKeyChars, 0, textInputKeyChars.length, textInputPt.x, textInputPt.y);
                        txtAnnotation = true;
                    }
                }
            }
        }
    }
}
