package com.yellow;

import sun.awt.ConstrainableGraphics;

import java.util.Objects;
import java.util.Properties;

/**
 * @Description 启动类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int x,y;
        String s = ConfigMgr.get("InitTankCount");
        int initTankCount = Integer.parseInt(s);
       /* int initTankCount = Integer.parseInt((String) Objects.requireNonNull(ConfigMgr.get("initTankCount")));*/
        for (int i = 0; i < initTankCount; i++) {
            x = (int)(Math.random()*900);
            y = (int)(Math.random()*500 );
            tankFrame.tanks.add(new Tank(x,y,Dir.DOWN,tankFrame,true,Group.BAD));
        }

        while(true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
