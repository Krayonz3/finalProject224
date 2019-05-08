/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Final.ninja.repaint(g);  
    }

}