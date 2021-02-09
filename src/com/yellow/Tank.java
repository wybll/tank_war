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
    private boolean living = true;
    private TankFrame tf;
    private static final int SPEED = 5;
    private boolean moving = false;
    public  int WIDTH = ResourceImage.tankL.getWidth();
    public  int HEIGHT = ResourceImage.tankL.getHeight();

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
        if(!living){
            tf.tanks.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceImage.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceImage.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceImage.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceImage.tankD,x,y,null);
                break;
        }
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

    public synchronized void fire() {
        int bx = this.x + WIDTH/2 - Bullet.WIDTH;
        int by = this.y + HEIGHT/2 - Bullet.HEIGHT;

        tf.bullets.add(new Bullet(bx,by,this.dir,this.tf));
    }

    public void die() {
        living = false;
    }
}
