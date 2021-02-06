package com.yellow;

/**
 * @Description 启动类
 * @Author backen
 * @Date 2021/2/5 23:11
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while(true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
