package davinci;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DaVinci extends JComponent implements ChangeListener {

    private DaVinciModel model;
    
    public void setModel(DaVinciModel lModel){
        if (model != null)
                model.removeChangeListener(this);
        model = lModel;
        model.addChangeListener(this);
    }
    
    public DaVinciModel getModel() {
        return model;
    }
    
    public void setUI(DaVinciUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        DaVinciUI d = (DaVinciUI)UIManager.getUI(this);
        setUI(d);
        invalidate();
    }
    
    public String getUIClassID() {
        return DaVinciUI.UI_CLASS_ID;
    } 
    
    public DaVinci(String fileName){
        super();
        System.out.println("works here too");
        setModel(new VinciModel(fileName));
        updateUI();
        
    }
    
    public static void main(String[] args) {
        
    }
    
    public void modelChanged(){
        repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
    }
    

    
}
