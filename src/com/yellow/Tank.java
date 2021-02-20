package com.yellow;

import org.junit.rules.TestName;

import java.awt.*;
import java.util.Random;

/**
 * @Description 坦克类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Tank {
    private int x,y;
    private Dir dir;
    private Group group = Group.BAD;
    private boolean living = true;
    private TankFrame tf;
    private static final int SPEED = 5;
    private boolean moving = true;
    private Random random = new Random();
    public  static int WIDTH = ResourceImage.tankL.getWidth();
    public  static int HEIGHT = ResourceImage.tankL.getHeight();
    Rectangle rectT= new Rectangle();

    public Tank(int x, int y, Dir dir, TankFrame tf,boolean moving,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.moving = moving;
        this.group = group;

        rectT.x = this.x;
        rectT.y = this.y;
        rectT.width = WIDTH;
        rectT.height = HEIGHT;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private void moving() {
        if(!moving){
            return;
        }
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

        rectT.x = this.x;
        rectT.y = this.y;

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();
        boundsCheck();
    }

    private void boundsCheck() {
        if (this.x < 2) this.x = 2;
        if (this.y < 28) this.y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) this.x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 5) this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 5;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public synchronized void fire() {
        int bx = this.x + WIDTH/2 - Bullet.WIDTH;
        int by = this.y + HEIGHT/2 - Bullet.HEIGHT;

        tf.bullets.add(new Bullet(bx,by,this.dir,this.tf,this.group));
    }

    public void die() {
        living = false;
    }
}
