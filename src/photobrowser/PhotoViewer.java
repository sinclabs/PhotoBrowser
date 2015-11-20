package photobrowser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import davinci.DaVinci; 

public class PhotoViewer extends JPanel {
    PhotoAwesome photo;
    DaVinci d;
    
    public PhotoViewer(){
        setPreferredSize(new Dimension(200, 200));
        setLayout(new BorderLayout());
        //setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setFocusable(true);
        //photo = new PhotoAwesome();
         
        //add(photo, BorderLayout.CENTER);
    }
    
    public void setPhoto(String photoPath){
        d = new DaVinci(photoPath);
        add(d);
        System.out.println("works "+photoPath);
        //photo.getImage(photoPath);
    }
}
