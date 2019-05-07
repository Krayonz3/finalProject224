package finalproject224;

/*
AUTHOR: CARLOS VAZQUEZ BAUR, JOEY TORII

*/


import Window.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jetor
 */
public class FinalProject224 extends JFrame{

    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH / 4 * 3;
    menu menuObject = new menu();
    
    public FinalProject224() {
        /*
        setTitle("CRAPPY PORTAL");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        */
        Frame.main(null);
    }
   
    
    public static void main(String[] args) {
 
        new FinalProject224();
    }
    
}
