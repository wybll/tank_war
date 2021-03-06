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
    private static final int SPEED = 20;
    public static  int WIDTH = ResourceMgr.bulletL.getWidth();
    public static  int HEIGHT = ResourceMgr.bulletL.getHeight();
//关于 static，该属性 被private修饰之后，是否还能别其他对象调用？
    private  /*static*/ boolean living = true;
    private Group group = Group.BAD;

    Rectangle rectB = new Rectangle();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public Bullet(int x, int y, Dir dir,TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rectB.x = this.x;
        rectB.y = this.y;
        rectB.width = WIDTH;
        rectB.height = HEIGHT;

        tf.bullets.add(this);
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
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
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

            rectB.x = this.x;
            rectB.y = this.y;

            if(x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) {
                living = false;
            }
        }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;

//        Rectangle rectB = new Rectangle(this.x,this.y,Bullet.WIDTH,Bullet.HEIGHT);
//        Rectangle rectT= new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (rectB.intersects(tank.rectT)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Exploed.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Exploed.HEIGHT/2;
            tf.exploeds.add(new Exploed(eX,eY,tf));
        }
    }

    private void die() {
        living = false;
    }
}
