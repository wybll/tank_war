package com.yellow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description 图片加载类
 * @Author backen
 * @Date 2021/2/9 15:32
 */
public class ResourceImage {

    public static BufferedImage tankL,tankR,tankU,tankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;

    //开辟 ‘爆炸图片’内存数组，将图片从硬盘中依次加载至内存中
    public static BufferedImage[] exploeds = new BufferedImage[16];

    static {
            try{
                ClassLoader cl = ResourceImage.class.getClassLoader();

                tankL = ImageIO.read(cl.getResourceAsStream("image/TankL.gif"));
                tankR = ImageIO.read(cl.getResourceAsStream("image/TankR.gif"));
                tankU = ImageIO.read(cl.getResourceAsStream("image/TankU.gif"));
                tankD = ImageIO.read(cl.getResourceAsStream("image/TankD.gif"));

                bulletL = ImageIO.read(cl.getResourceAsStream("image/bulletL.gif"));
                bulletR = ImageIO.read(cl.getResourceAsStream("image/bulletR.gif"));
                bulletU = ImageIO.read(cl.getResourceAsStream("image/bulletU.gif"));
                bulletD = ImageIO.read(cl.getResourceAsStream("image/bulletD.gif"));

                for (int i = 0; i < 16; i++) {
                    exploeds[i] = ImageIO.read(cl.getResourceAsStream("image/e"+ (i+1)+".gif"));
                }

            }catch (IOException exception){
                exception.printStackTrace();
            }
    }

}
