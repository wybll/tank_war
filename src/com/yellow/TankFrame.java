package com.yellow;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 *@Description TODO
 *@Author backen
 *@Date 2021/2/5 10:15
 *
 */
public class TankFrame extends Frame {
    int x = 200,y = 200;
    Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

    public TankFrame() throws HeadlessException {
        setSize(1200,675);
        setVisible(true);//设置可见
        setTitle("tank_war");
        setResizable(false);//设置不可改变大小

        addKeyListener(new MyKeyListener());
       //添加一个监听器（监听关闭事件）
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x,y,30,30);
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


    }
//  定制坦克的键盘适配器
    class MyKeyListener extends KeyAdapter {
        boolean bL =false;
        boolean bR =false;
        boolean bU =false;
        boolean bD =false;
        
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                 case KeyEvent.VK_LEFT:
                     bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }setMainTankDir();
        }

        private void setMainTankDir(){
            if(bL) dir = Dir.LEFT;
            if(bR) dir = Dir.RIGHT;
            if(bU) dir = Dir.UP;
            if(bD) dir = Dir.DOWN;
        }
    }


}
