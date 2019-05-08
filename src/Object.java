/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import javax.swing.*;
import java.awt.*;

public class Object {
    
    public Image imj;
    public int x;
    public int y;
    public int length;
    public int heigh;
    
    public Object() {
        this(0, 0, 0, 0,"");
    }

    public Object(Object r) {
        this(r.x, r.y, r.length, r.heigh,"");
    }

    public Object(int x, int y, int width, int height, String obstacle ) {
        this.x = x;
        this.y = y;
        this.length = width;
        this.heigh = height;
        this.imj = new ImageIcon(obstacle).getImage();
    }

    public Object(int width, int height) {
        this(0, 0, width, height,"");
    }

    public boolean intersects(Object r) {
        int twidth = this.length;
        int theight = this.heigh;
        int ninjaw = r.length;
        int ninjah = r.heigh;
        if (ninjaw <= 0 || ninjah <= 0 || twidth <= 0 || theight <= 0) {
            return false;
        }
        int object1x = this.x;
        int object1y = this.y;
        int object2x = r.x;
        int object2y = r.y;
        ninjaw += object2x;
        ninjah += object2y;
        twidth += object1x;
        theight += object1y;
       
        return ((ninjaw < object2x || ninjaw > object1x) && (ninjah < object2y || ninjah > object1y) && (twidth < object1x || twidth > object2x) && (theight < object1y || theight > object2y));
    }

}