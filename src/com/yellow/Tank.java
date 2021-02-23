package com.yellow;

import java.awt.*;
import java.util.Random;

/**
 * @Description 坦克类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Tank {
    int x,y;
    Dir dir;
    Group group;
    private boolean living = true;
    TankFrame tf;
    private static final int SPEED = 5;
    private boolean moving = true;
    private Random random = new Random();
    public  static int WIDTH = ResourceMgr.tankL.getWidth();
    public  static int HEIGHT = ResourceMgr.tankL.getHeight();
    Rectangle rectT= new Rectangle();
    FireStrategy fs ;


    public Tank(int x, int y, Dir dir, TankFrame tf,boolean moving,Group group)  {
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

        if (this.group == Group.GOOD) {
            String goodFSName = ConfigMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFSName).newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }else {
            String badFSName = ConfigMgr.get("badFS");
            try {
                fs = (FireStrategy) Class.forName(badFSName).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
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
        fs.fire(this);
    }

    public void die() {
        living = false;
    }
}
