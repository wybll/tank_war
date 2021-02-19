package com.yellow;

/**
 * @Description 启动类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int x,y;

        for (int i = 0; i < 5; i++) {
            x = (int)(Math.random()*900);
            y = (int)(Math.random()*500 );
            tankFrame.tanks.add(new Tank(x,y,Dir.DOWN,tankFrame));
        }

        while(true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
