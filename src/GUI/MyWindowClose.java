package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author AD
 */
public class MyWindowClose extends WindowAdapter{

    @Override
    public void windowClosing(WindowEvent we) {
      System.exit(0);
    }
    
}
