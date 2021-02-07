package com.yellow;

import java.awt.*;

/**
 * @Description 子弹类
 * @Author backen
 * @Date 2021/2/6 14:53
 */
public class Bullet {
    private int x,y;
    private Dir dir;
    TankFrame tf;
    private static final int SPEED = 10;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
//关于 static，该属性 被private修饰之后，是否还能别其他对象调用？
    private  /*static*/ boolean live = true;

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

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        moving();
        if(!live){
            tf.bullets.remove(this);
        }

    }

    private void moving() {
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
            if(x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) {
                live = false;
            }else {
                live = true;
            }
        }
}
