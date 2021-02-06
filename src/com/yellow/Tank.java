package com.yellow;

import java.awt.*;
import java.util.ArrayList;

/**
 * @Description 坦克类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Tank {
    private int x,y;
    private Dir dir;
    private TankFrame tf;
    private static final int SPEED = 5;
    private boolean moving = false;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){

        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        moving();

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void moving() {
        if(moving){
            switch (dir){
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
            }
        }

    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x+10,this.y+10,this.dir));
    }
}
