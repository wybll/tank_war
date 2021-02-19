package test;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @Description 测试类
 * @Author backen
 * @Date 2021/2/9 14:51
 */
public class ImageTest {

    @Test
    public void test(){
            try{
               BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("image/tankL.gif"));
                Assert.assertNotNull(image);

            }catch(Exception e)
                {
                    e.printStackTrace();
                }
    }
}
