package com.yellow;

import java.awt.*;

/**
 * @Description “爆炸图片”类
 * @Author backen
 * @Date 2021/2/19 16:45
 */
public class Exploeds {

    private int x,y;

    TankFrame tf;

    public static  int WIDTH = ResourceImage.exploeds[0].getWidth();
    public static  int HEIGHT = ResourceImage.exploeds[0].getHeight();

    int temp = 0 ;

    public Exploeds(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    //关于 static，该属性 被private修饰之后，是否还能别其他对象调用？
    private  /*static*/ boolean living = true;

    public void paint(Graphics g){
        /*for (int i = 0; i < ResourceImage.exploeds.length; i++) {
            g.drawImage(ResourceImage.exploeds[i],x,y,null);
            //temp++;
        }*/
        g.drawImage(ResourceImage.exploeds[temp++],x,y,null);
        if (temp >= ResourceImage.exploeds.length) temp = 0;
    }

}
