/*
@name: Joseph Torii and Carlos Vasquez
@assignment: Final Project
@date: May 8th, 2019
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Final implements ActionListener, KeyListener {

    public static Final ninja;
    private Renderer renderer;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;       
    private ArrayList<Object> obstacle;  
    private Random random;
    private int moment = 0;
    private int fall = 0;       
    private boolean Over,Play;           
    private int score = 0;
    private int highScore = 0;
    private Image background;
    private int temp = 0;         
    private int move = 0;      
    private Object player;
    private int cd = 0;  
    private int nrSpace = 0;
    String[] imag = {"ninja.png", "ninja2.png", "ninjac.png", "ninjac.png"};

    public Final() {
   
        JFrame Game = new JFrame();
        //menu menuObject = new menu();
        renderer = new Renderer();
        Game.add(renderer);
        random = new Random();
        player = new Object(100,HEIGHT-125,90,90,imag[temp]);
        obstacle = new ArrayList<Object>();
        obstacle.add(new Object(-100, HEIGHT - 110, 70, 70,"ninjablock.png"));
            background = new ImageIcon("background1.jpg").getImage();
        Game.setTitle("Crappy Portal");
        Timer timer = new Timer(15, this);  
        Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game.setSize(WIDTH, HEIGHT);
        Game.addKeyListener(this);
        Game.setResizable(false);
        Game.setVisible(true);
        timer.start();
        addObstacle(true);
    }

    private void addObstacle(boolean start) {
        int obs = random.nextInt(60);
        int widthC = 30 + random.nextInt(60); 
        int difObs = 450 + random.nextInt(650);
        if(obs%7 != 0)
        if(start){
            obstacle.add(new Object(WIDTH + widthC + obstacle.size() * difObs, HEIGHT - 110, widthC, 70,"trashcan2.png"));
        }
        else {
            obstacle.add(new Object(obstacle.get(obstacle.size()-1).x + difObs, HEIGHT - 110, widthC,70,"trashcan2.png"));
        }
        else
        if(start){
            obstacle.add(new Object(WIDTH + widthC + obstacle.size() * difObs, HEIGHT - 175, 90, 70,"ninjastar.png"));
        }
        else {
            obstacle.add(new Object(obstacle.get(obstacle.size()-1).x + difObs, HEIGHT - 175 , 90, 70,"ninjastar.png"));
        }
    }

    public void actionPerformed(ActionEvent e){
        if(Play) {
            int speed = 15;

            for (int i = 0; i < obstacle.size(); i++){
                Object can = obstacle.get(i);
                can.x -= speed; 
            }
            for (int i = 0; i < obstacle.size() && !Over; i++) {
                Object can = obstacle.get(i);
                if (can.x + can.length < 0) {
                    obstacle.remove(can);
                    addObstacle(false); 
                }
            }
            player = new Object(100, HEIGHT - 125, 90, 90, imag[temp]);  
            moment++;
            if(moment%3 == 0)
                score++;          
            if (((moment % 2) == 0) && (fall < 18)){
                fall = fall + 20; 
            }
            if (fall < 18) {
                if (move == 0) {
                    player.y += fall;
                } else {
                    player.y -= fall;
                }
            } 
            else {
                player.y = HEIGHT - 125;
            }
            if (player.y == HEIGHT - 125) {
                move = 0;
                if(temp == 0 || temp == 1) {   
                    temp++;
                    if (temp > 1) {
                        temp = 0;
                    }
                }
                else
                if(temp == 2 || temp == 3){           
                    player.y = HEIGHT - 96;
                    temp++;
                    if (temp > 3) {
                        temp = 2;
                    }
                }
            }
        }
        for(Object thing: obstacle){        // if the player hits an obstacle
            if(thing.intersects(player)){
                Over = true;
                Play = false;     
                player.x = thing.x-player.length;
            }
        }
        renderer.repaint();
        }

    public void repaint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.CYAN);
        g.fillRect(0, HEIGHT-45, WIDTH, 100);    
        if(!Over) {
            g.drawImage(player.imj, player.x, player.y, null);      
        }

        for(Object thing : obstacle) {
            g.drawImage(thing.imj, thing.x, thing.y, thing.length, thing.heigh,null);     
        }

        g.setColor(Color.white.darker() );
        g.setFont(new Font("Arial",1,25));
        if(!Play){
            g.drawString("Use SPACE to Teleport",280,100);
            g.drawString("Use DOWN Arrow to crouch",240,120);
        }
        g.setColor(Color.white.darker().darker());
        g.setFont(new Font("Arial",1,100));
        if(Over) {
            g.drawString("GAME OVER", 120, HEIGHT / 2 - 50);
            g.setFont(new Font("Arial",1,30));
            g.drawString("space to restart", 250, HEIGHT / 2 - 20);
            
            g.setColor(Color.gray.brighter());
            g.setFont(new Font("Arial",1,20));
            g.drawString("HIGHSCORE:"+String.valueOf(highScore),WIDTH-200,40);  
            g.drawString("SCORE:"+String.valueOf(score),WIDTH-200,70);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN && player.y == HEIGHT-125){
            temp = 2;
         }

        if(e.getKeyCode() == KeyEvent.VK_SPACE && player.y == HEIGHT-125 &&!Over &&(temp == 0 || temp == 1)){
            jump();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_DOWN ){
            temp = 1;
        }

        if(e.getKeyCode()==KeyEvent.VK_SPACE && Over){
            nrSpace++;
            move = 1;
            if(nrSpace==2) {        
                jump();
            }
        }
    }
    private void jump() {
        if(Over) {  // resets all values if the game ends
            nrSpace = 0;
            Over = false;
            player = new Object(100, HEIGHT-125, 90, 90,imag[temp]);
            obstacle.clear(); 
            addObstacle(true);
            addObstacle(true);
            if(score > highScore){
                highScore = score; 
                score = 0;
                fall = 0;
            }
        }
        if(!Play)   // restarts the game
            Play = true;
        else if(!Over){
            if(fall > 0){
                fall = 0;
            }
            fall -= cd;
            if(cd < 100) {  // rate at which the player falls
                cd += 20;
                jump();
            }
            else{
               cd=0;
            }
        }
    }
    public static void main(String[] args) {
        ninja = new Final();
    }
}