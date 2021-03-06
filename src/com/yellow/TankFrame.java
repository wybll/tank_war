package com.yellow;


import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *
 *@Description 坦克战场
 *@Author backen
 *@Date 2021/2/5 10:15
 *
 */
public class TankFrame extends Frame {

    static final int GAME_WIDTH = 1200;
    static final int GAME_HEIGHT = 675;

    public List<Tank> tanks = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Exploed> exploeds = new ArrayList<>();
    Tank mytank = new Tank(200,200, Dir.UP,this,false,Group.GOOD);

    Exploed exploed = new Exploed(100,100,this);

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH,GAME_HEIGHT);
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


    //用来消除屏幕闪烁
   /* Image offScreenImange = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImange == null){
            offScreenImange = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImange.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.WHITE);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        paint(gOffScreen);
        gOffScreen.setColor(c);
        g.drawImage(offScreenImange,0,0,null);
    }*/
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public  void paint(Graphics g) {

        mytank.paint(g);

        Color c  = g.getColor();
        g.setColor(Color.BLUE);
        g.drawString("当前子弹数量："+bullets.size(),10,50);
        g.drawString("当前敌人数量："+tanks.size(),10,70);
        g.setColor(c);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for (int i = 0; i < exploeds.size(); i++) {
            exploeds.get(i).paint(g);
        }


    }
//  定制坦克的键盘适配器
    class MyKeyListener extends KeyAdapter {
        boolean bL =false;
        boolean bR =false;
        boolean bU =false;
        boolean bD =false;
        
        @Override
        public synchronized void keyPressed(KeyEvent e) {
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
        public synchronized void keyReleased(KeyEvent e) {
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
                case KeyEvent.VK_CONTROL:
                    mytank.fire();
                default:
                    break;
            }setMainTankDir();

        }
//      设置主战坦克方向
        private void setMainTankDir(){
            //如果四个方向键 都没有按下，moving=false; return
            if( !bL && !bR && !bU && !bD) mytank.setMoving(false);
            else {
                mytank.setMoving(true);
                if(bL) mytank.setDir (Dir.LEFT);
                if(bR) mytank.setDir(Dir.RIGHT);
                if(bU) mytank.setDir(Dir.UP);
                if(bD) mytank.setDir(Dir.DOWN);
            }

        }
    }


}
