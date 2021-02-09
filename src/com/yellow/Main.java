package com.yellow;

/**
 * @Description 启动类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(100+i*80,50,Dir.DOWN,tankFrame));
        }

        while(true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
