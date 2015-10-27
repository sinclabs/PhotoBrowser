package photobrowser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PhotoViewer extends JPanel {
    PhotoAwesome photo;
    
    public PhotoViewer(){
        this.setSize(200, 200);
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setFocusable(true);
        photo = new PhotoAwesome();
        add(photo);
    }
    
    public void setPhoto(String photoPath){
        photo.getImage(photoPath);
    }
}
