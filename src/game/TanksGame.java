package game;

import static game.TanksGame.mainP;
import java.awt.Color;
import java.util.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.*;

public class TanksGame {
    public static void main(String args[]){
        game();
    }
    public static void menu(){
        
    }
    public static GamePanel mainP = new GamePanel();
    public static void game(){
        JFrame mainF = new JFrame();
        mainF.setSize(1200,800);
        mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainF.setLayout(gbl);
        mainF.add(mainP,gbc);
        mainF.setVisible(true);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mainP.repaint();
            }
        }, 0, 1000/60);
    }
}
class GamePanel extends JPanel{
    GamePanel t = this;
    ArrayList<tank> tanks = new ArrayList<>();
    private BufferedImage buffer = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
    GamePanel(){
        tanks.add(new tank());
        tanks.add(new tank());
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        //<editor-fold defaultstate="collapsed" desc="Movement Keybinds">
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "move1-left");
            am.put("move1-left", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveLeft(true);
                    t.repaint();
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "move1-right");
            am.put("move1-right", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveRight(true);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "move1-up");
            am.put("move1-up", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveUp(true);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "move1-down");
            am.put("move1-down", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveDown(true);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "move1-left-off");
            am.put("move1-left-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveLeft(false);
                    t.repaint();
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "move1-right-off");
            am.put("move1-right-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveRight(false);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "move1-up-off");
            am.put("move1-up-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveUp(false);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "move1-down-off");
            am.put("move1-down-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(0).moveDown(false);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "move2-left");
            am.put("move2-left", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveLeft(true);
                    t.repaint();
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "move2-right");
            am.put("move2-right", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveRight(true);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "move2-up");
            am.put("move2-up", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveUp(true);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "move2-down");
            am.put("move2-down", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveDown(true);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "move2-left-off");
            am.put("move2-left-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveLeft(false);
                    t.repaint();
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "move2-right-off");
            am.put("move2-right-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveRight(false);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "move2-up-off");
            am.put("move2-up-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveUp(false);
                }
            });
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "move2-down-off");
            am.put("move2-down-off", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    tanks.get(1).moveDown(false);
                }
            });
        //</editor-fold>
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(buffer.getWidth() != t.getWidth() || buffer.getHeight() != t.getHeight()){
                    try{
                        buffer = new BufferedImage(t.getWidth(), t.getHeight(), BufferedImage.TYPE_INT_RGB);
                    }catch(IllegalArgumentException e){
                        
                    }
                }
                t.repaint();
            }
        }, 0, 1000 / 60);
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D buffG = (Graphics2D)buffer.getGraphics();
        //<editor-fold desc="Antialiasing">
            buffG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            buffG.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //</editor-fold>
        buffG.setColor(Color.WHITE);
        buffG.fillRect(0, 0, mainP.getWidth(), mainP.getHeight());
        
        for(int i = 0; i < tanks.size(); i++){
            buffG.setColor(tanks.get(i).color);
            buffG.fillRect(tanks.get(i).x, tanks.get(i).y, tanks.get(i).size, tanks.get(i).size);
            buffG.setColor(Color.BLACK);
            buffG.drawString(""+(i+1), tanks.get(i).x+tanks.get(i).size/2-5, tanks.get(i).y+tanks.get(i).size/2+5);
        }
        g.drawImage(buffer, 0, 0, null);
    }
}
class tank{
    Color color = DanColors.randomHSB();
    int health = 100;
    int x = 500, y = 100;
    int size = 50;
    double speed = 5;
    double initXLSpeed, initXRSpeed, initYUSpeed, initYDSpeed;
    boolean leftPressed = false, rightPressed = false, upPressed = false, downPressed = false;
    tank(){
        System.out.println(this.color);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(leftPressed){
                    if(initXLSpeed < speed)
                        initXLSpeed+=0.5;
                    else
                        initXLSpeed = speed;
                    if(x > 0)
                        x -= speed; 
                }
                if(rightPressed){
                    if(initXRSpeed <= speed)
                        initXRSpeed+=0.5;
                    else
                        initXRSpeed = speed;
                    if(x < mainP.getWidth()-size)
                        x += speed;
                }
                if(upPressed){
                    if(initYUSpeed <= speed)
                        initYUSpeed+=0.5;
                    else
                        initYUSpeed = speed;
                    if(y > 0)
                        y -= speed;
                }
                if(downPressed){
                    if(initYDSpeed <= speed)
                            initYDSpeed+=0.5;
                    else
                        initYDSpeed = speed;
                    if(y < mainP.getHeight()-size)
                        y += speed;
                }
            }
        }, 0, 1000 / 60);
    }
    public void moveLeft(Boolean pressed){
        initXLSpeed = 0;
        if(pressed)
            leftPressed = true;
        else
            leftPressed = false;
    }
    public void moveRight(Boolean pressed){
        initXRSpeed = 0;
        if(pressed)
            rightPressed = true;
        else
            rightPressed = false;
    }
    public void moveUp(Boolean pressed){
        initYUSpeed = 0;
        if(pressed)
            upPressed = true;
        else
            upPressed = false;
    }
    public void moveDown(Boolean pressed){
        initYDSpeed = 0;
        if(pressed)
            downPressed = true;
        else
            downPressed = false;
    }
}
class shell{
    
}
class DanColors{
    public static Color randomRGB() {
        return new Color((int)(Math.floor(Math.random()*255)),(int)(Math.floor(Math.random()*255)),(int)(Math.floor(Math.random()*255)));
    }
    public static Color randomHSB(){
        return Color.getHSBColor((float)Math.random(), 1, 1);
    }
}