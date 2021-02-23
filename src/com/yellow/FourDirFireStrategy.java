package com.yellow;

/**
 * @Description TODO
 * @Author backen
 * @Date 2021/2/23 23:01
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int bx = t.x + t.WIDTH/2 - Bullet.WIDTH;
        int by = t.y + t.HEIGHT/2 - Bullet.HEIGHT;
        Dir[] dirs = Dir.values();
        for (int i = 0; i < dirs.length; i++) {
            new Bullet(bx,by,dirs[i],t.tf,t.group);
        }

    }
}
