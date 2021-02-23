package com.yellow;

/**
 * @Description TODO
 * @Author backen
 * @Date 2021/2/23 22:48
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int bx = t.x + t.WIDTH/2 - Bullet.WIDTH;
        int by = t.y + t.HEIGHT/2 - Bullet.HEIGHT;

        new Bullet(bx,by,t.dir,t.tf,t.group);
    }
}
