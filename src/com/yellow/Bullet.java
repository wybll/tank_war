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
    public static final int WIDTH = ResourceImage.bulletL.getWidth();
    public static final int HEIGHT = ResourceImage.bulletL.getHeight();
//关于 static，该属性 被private修饰之后，是否还能别其他对象调用？
    private  /*static*/ boolean living = true;


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
        if(!living){
            tf.bullets.remove(this);
        }
        /*Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);*/

        switch (dir){
            case LEFT:
                g.drawImage(ResourceImage.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceImage.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceImage.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceImage.bulletD,x,y,null);
                break;
        }

        moving();

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
                living = false;
            }else {
                living = true;
            }
        }

    public void collideWith(Tank tank) {
        Rectangle rectB = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rectT= new Rectangle(tank.getX(),tank.getY(),tank.WIDTH,tank.HEIGHT);
        if (rectB.intersects(rectT)){
            tank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }
}
