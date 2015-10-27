package photobrowser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class PhotoAwesome extends JComponent {
    private boolean flipped = false;
    private boolean isPhoto = false;
    private boolean isAnnotated = false;
    private boolean textInput = false;
    private SketchPath path;
    private Point textInputPt = new Point();
    private ArrayList<Character> textInputKey;
    private boolean textInputKeyStart = false;
    private boolean txtAnnotation = false;
   
    private BufferedImage photo;
    
    public PhotoAwesome(){
        super();
        this.setSize(640, 460);
        path = new SketchPath();
        this.textInputKey = new ArrayList<>();
        setFocusable(true);
        
        this.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        requestFocus();
                        System.out.println("kt");
                        if((int)e.getKeyChar() == 8)
                            textInputKey.remove(textInputKey.size()-1);
                        else if((int)e.getKeyChar() == 27)
                            textInput = false;
                        else{
                            textInputKey.add(e.getKeyChar());
                            textInputKeyStart = true;
                        }
                        repaint();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        System.out.println("kp");
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        System.out.println("kr");
                    }
                }
        );
        
        addMouseMotionListener(
                new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        path.setPath(e.getPoint());
                        isAnnotated = true;
                        repaint();
                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {
                        
                    }
                }
        );
        
        addMouseListener(
            new MouseListener(){
                @Override
                public void mouseReleased(MouseEvent e){
                    if(flipped && isAnnotated){
                        repaint();
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e){
                     
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    
                }
                
                @Override
                public void mouseEntered(MouseEvent e){
                    
                }
                
                @Override
                public void mouseClicked(MouseEvent e){
                    if(e.getClickCount() == 2){
                        if(!flipped){
                            flipped = true;
                            repaint();
                        }
                        else{
                            flipped = false;
                            repaint();
                        }
                    }
                    else if(e.getClickCount() == 1){
                        if(flipped){
                            if(textInput)
                                textInput = false;
                            else{
                                textInput = true;
                                textInputPt = e.getPoint(); 
                            }
                            requestFocus();
                            repaint();
                        }
                    }
                }
            }
        );
    }
    
    public void getImage(String filePath) {
       try {                
          photo = ImageIO.read(new File(filePath));
          isPhoto = true;
       } 
       catch (IOException ex) {
           
       }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if(isPhoto){
            super.paintComponent(g);
            if(!flipped){
                g.drawImage(photo, 0, 0, null);         
            }
            else{
                g.setColor(Color.white);
                g.fillRect(0, 0, super.getHeight(), super.getWidth());
                if(isAnnotated){
                    ArrayList<Point> pth = path.getPath();
                    int i = 0;
                    while(i < pth.size()){
                        g.setColor(Color.red);
                        if(i!=0)
                            g.drawLine(pth.get(i-1).x, pth.get(i-1).y, pth.get(i).x, pth.get(i).y);
                        else
                            g.drawLine(pth.get(i).x, pth.get(i).y, pth.get(i).x, pth.get(i).y);
                        i++;
                    }
                }
                if(textInputKeyStart && !textInput){
                    System.out.println("b");
                    g.setColor(Color.black);
                    char[] textInputKeyChars = new char[textInputKey.size()];
                    for(int i = 0; i<textInputKey.size(); i++){
                        textInputKeyChars[i] = textInputKey.get(i);
                    }
                    g.drawChars(textInputKeyChars, 0, textInputKeyChars.length, 100, 100);
                }
                if(textInput){
                    System.out.println("a");
                    g.setColor(Color.black);
                    g.drawLine(textInputPt.x, textInputPt.y, textInputPt.x, textInputPt.y-10);
                    if(textInputKeyStart){
                        System.out.println("b");
                        char[] textInputKeyChars = new char[textInputKey.size()];
                        for(int i = 0; i<textInputKey.size(); i++){
                            textInputKeyChars[i] = textInputKey.get(i);
                        }
                        g.drawChars(textInputKeyChars, 0, textInputKeyChars.length, textInputPt.x, textInputPt.y);
                        txtAnnotation = true;
                    }
                }
            }
        }
    }   
}
