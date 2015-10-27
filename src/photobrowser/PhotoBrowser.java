package photobrowser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PhotoBrowser {
    
    //Private Variables
    private JFrame mainWindow;
    private JLabel statusLabel;
    private JLabel statusbar;
    private PhotoViewer photoPanel;
    
    public PhotoBrowser(){
        createGUI();
    }
    
    
    private void createComponents(Container frame)  {
        frame.setLayout(new BorderLayout());
        frame.setFocusable(true);
        frame.add(createMenuBar(), BorderLayout.PAGE_START);
        JPanel togButtons = new JPanel();
        JToggleButton family = new JToggleButton("Family");
        family.addItemListener(new ItemListener( ) {
            @Override
            public void itemStateChanged(ItemEvent ev) {
              if(ev.getStateChange()==ItemEvent.SELECTED){
                  statusbar.setText("Family On");
              } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                  statusbar.setText("Family Off");
              }
            }
        });
        JToggleButton vacation = new JToggleButton("Vacation");
        vacation.addItemListener(new ItemListener( ) {
            @Override
            public void itemStateChanged(ItemEvent ev) {
              if(ev.getStateChange()==ItemEvent.SELECTED){
                  statusbar.setText("Vacation On");
              } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                  statusbar.setText("Vacation Off");
              }
            }
        });
        JToggleButton awesome = new JToggleButton("Awesome Compilation");
        awesome.addItemListener(new ItemListener( ) {
            @Override
            public void itemStateChanged(ItemEvent ev) {
              if(ev.getStateChange()==ItemEvent.SELECTED){
                  statusbar.setText("Awesome Compilation On");
              } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                  statusbar.setText("Awesome Compilation Off");
              }
            }
        });
        togButtons.add(family);
        togButtons.add(vacation);
        togButtons.add(awesome);
        frame.add(togButtons, BorderLayout.LINE_START);
        photoPanel = new PhotoViewer();
        photoPanel.setPhoto("C:\\img.jpg");
        frame.add(photoPanel, BorderLayout.CENTER);
        statusbar = new JLabel();
        statusbar.setBorder(BorderFactory.createEtchedBorder());
        statusbar.setText("Ready");
        frame.add(statusbar, BorderLayout.PAGE_END);
    }
    
    private JMenuBar createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Quit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_Q);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        JMenuItem importMenuItem = new JMenuItem("Import");
        importMenuItem.setMnemonic(KeyEvent.VK_I);
        importMenuItem.setToolTipText("Import Folder");
        importMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                statusbar.setText("Import Folder Pressed");
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);
            }
        });
        
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.setMnemonic(KeyEvent.VK_D);
        deleteMenuItem.setToolTipText("Delete Photo");
        deleteMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                statusbar.setText("Delete Photo Pressed");
            }
        });
        file.add(deleteMenuItem);
        file.add(importMenuItem);
        file.add(eMenuItem);

        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        ButtonGroup viewGroup = new ButtonGroup();
        JRadioButtonMenuItem viewRadio = new JRadioButtonMenuItem("Photo viewer");
        viewRadio.setSelected(true);
        viewGroup.add(viewRadio);
        view.add(viewRadio);
        viewRadio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                statusbar.setText("Photo viewer Pressed");
            }
        });
        JRadioButtonMenuItem browseRadio = new JRadioButtonMenuItem("Browser");
        viewGroup.add(browseRadio);
        view.add(browseRadio);
        browseRadio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                statusbar.setText("Browser Pressed");
            }
        });
        JRadioButtonMenuItem spiltRadio = new JRadioButtonMenuItem("Split mode");
        viewGroup.add(spiltRadio);
        view.add(spiltRadio);
        spiltRadio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                statusbar.setText("Split mode Pressed");
            }
        });
        
        menubar.add(file);
        menubar.add(view);
        return menubar;
    }
    
    private void createGUI() {
        //Create window.
        mainWindow = new JFrame("Photo Awesome v0.0.0");
        mainWindow.setSize(900,500);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //mainWindow.setAlwaysOnTop(true);

        //Create and Add Components
        createComponents(mainWindow.getContentPane());
        
        //Display the window.
        //mainWindow.pack();
        //mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        PhotoBrowser photoBrowser = new PhotoBrowser();
    }
}
