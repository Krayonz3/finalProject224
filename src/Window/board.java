package Window;

import java.awt.*;                                                                           
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class board extends JPanel implements ActionListener {
    player p;                                                                                
    Image background, menuBg, block;                                                                
    Timer time;                                                                              
    private menu Menu;
    private Frame Frame;
    Random blockGenerator = new Random();
    final int BKMAX_X = 10000;
    public boolean block1 = false;
    public boolean block2 = false;
    public boolean block3 = false;
    public boolean block4 = false;
    public int block1X, block2X, block3X, block4X;
    public int block1Y, block2Y, block3Y, block4Y;

    public static enum STATE {MENU,GAME};

    public static STATE State = STATE.MENU;

    public board() {
        this.addMouseListener(new mouseInput());
        p = new player();                                                                    
        Menu = new menu();
        addKeyListener(new woah());                                                            
        setFocusable(true);                                                                                                                          
        ImageIcon i = new ImageIcon("images/mmbackground.png");                 
        menuBg = i.getImage();
        i = new ImageIcon("images/background.png");  
        background = i.getImage(); 
        i = new ImageIcon("images/A.png");
        block = i.getImage();
        time = new Timer(20,this);                                                           
        time.start();        
    }
    
    
    public void actionPerformed(ActionEvent e){
        p.move();                                                                            
        repaint();                                                                           
    }
    
    public void randomBlockGenerator(int blockX, int blockY){
        int n = blockGenerator.nextInt(10);
        switch(n){
            case 1:
                block1 = true;
                block1X = blockX;
                block1Y = blockY;
                break;
            case 2:
                block2 = true;
                block2X = blockX;
                block2Y = blockY;
                break;
            case 3:
                block3 = true;
                block3X = blockX;
                block3Y = blockY;
                break;
            case 4:
                block4 = true;
                block4X = blockX;
                block4Y = blockY;
                break;
            default:
                break;
        }
    }
    public void paintComponent(Graphics g){                                                 
        if(State==STATE.GAME){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;                                            
            g2d.drawImage(background, -p.nx, 0, null);                                   
            g2d.drawImage(background, -p.nx2, 0, null);                                 
            if(-p.nx<-575)                                                              
                p.nx=-575;                                                              
            else if(-p.nx>575)                                                         
                p.nx=575;                                                               
            if(-p.nx2<-575)                                                           
                p.nx2=-575;                                                            
            else if(-p.nx2>575)                                                        
                p.nx2=575;
            
            //will generate a new block as long as the others are not being rendered as well
            if(!block1 && !block2 && !block3 && !block4)
                randomBlockGenerator(p.getX()+50, p.getY());
            
            if(block1){
                if (p.getX() <= block1X){
                    g2d.drawImage(block, block1X, block1Y, null);
                    if (p.getX() == block1X && p.getY() == block1Y)
                       System.exit(0);
                }
                else
                    block1 = false;
            }
            
            if(block2){
                //g2d.drawImage(block, block2X, block2Y + 30, null);
                if (p.getX() <= block2X){
                    g2d.drawImage(block, block2X, block2Y, null);
                    if (p.getX() == block2X && p.getY() == block2Y)
                       System.exit(0);
                }
                else
                    block2 = false;
            }
            
            if(block3){
                if(p.getX() <= block3X){
                    g2d.drawImage(block, block3X, block3Y, null);
                    if (p.getX() == block3X && p.getY() == block3Y)
                       System.exit(0);
                }
                else
                    block3 = false;
            }
            
            if(block4){
               // g2d.drawImage(block, block4X, block4Y + 30, null);
                if (p.getX() <= block4X){
                    g2d.drawImage(block, block4X, block4Y, null);
                    if (p.getX() == block4X && p.getY() == block4Y)
                       System.exit(0);
                }
                else
                    block4 = false;
            }
            
            g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
        } 
        else{
            g.drawImage(menuBg, 0, 0, null);
            menu.render(g);
        }
    }
    
    

    private class woah extends KeyAdapter {                                                   
        public void keyPressed(KeyEvent e) {                                                 
            p.keyPressed(e);                                                                 
        }
          
        public void keyReleased(KeyEvent e) {                                              
            p.keyReleased(e);                                                                
        }
    }
}