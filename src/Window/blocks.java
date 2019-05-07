package Window;

import java.awt.*;

public class blocks{
    
    //value indicates a clear area
    private static final int CLEAR = 0;
    
    //indicates an occupied area
    private static final int NOT_CLEAR = 1;
    
    //the width of the grid of our map
    private static final int WIDTH = 25;
    
    //the height of the grid 
    private static final int HEIGHT = 25;
    
    //the rendered size of the block
    public static final int BLOCK_SIZE = 64;
    
    private int[][] data = new int[WIDTH][HEIGHT];
    
    //create a new map
    public blocks(){
        for(int y = 0; y < HEIGHT; y++){
            data[0][y] = NOT_CLEAR;
            data[2][y] = NOT_CLEAR;
            data[7][y] = NOT_CLEAR;
            data[11][y] = NOT_CLEAR;
            data[15][y] = NOT_CLEAR;
            data[21][y] = NOT_CLEAR;
            data[WIDTH - 1][y] = NOT_CLEAR;            
        }
        
        for(int x = 0; x < WIDTH; x++){
            if ((x > 0) && (x < WIDTH - 1)){
                data[x][10] = CLEAR;                
            }
            
            if (x > 2){
                data[x][9] = NOT_CLEAR;
            }
            
            data[x][0] = NOT_CLEAR;
            data[x][HEIGHT - 1] = NOT_CLEAR;
        }
        
        data[4][9] = CLEAR;
        data[7][5] = CLEAR;
        data[7][5] = CLEAR;
        data[11][7] = CLEAR;
    }
    
    //renders the tile map...will be called in the board class
    public void paint(Graphics2D g){
        //loop through all tiles in the map to render
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                //if the cell is blocked, it will draw it                
                int alpha = 0;
                Color transparent;
                transparent = new Color(0, 0, 0, alpha);
                g.setColor(Color.white);
                if (data[x][y] == NOT_CLEAR){
                    g.setColor(Color.black);                    
                }
                
                g.fillRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }    
        }
    }
    
    public boolean blocked(float x, float y){
        
        return data[(int) x][(int)y] == NOT_CLEAR;
    }
    
}