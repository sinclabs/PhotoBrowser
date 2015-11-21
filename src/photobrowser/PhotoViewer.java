package photobrowser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import davinci.DaVinci; 

public class PhotoViewer extends JPanel {
    DaVinci d;
    
    public PhotoViewer(){
        setPreferredSize(new Dimension(200, 200));
        setLayout(new BorderLayout());
        setFocusable(true);
    }
    
    public void setPhoto(String photoPath){
        d = new DaVinci(photoPath);
        add(d);
    }
}
