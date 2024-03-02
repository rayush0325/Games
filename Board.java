package myPack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//here we declared Board as panel so as to add its code to the frame
public class Board extends Frame implements ActionListener,KeyListener {
    static Image head;
    static Image dot;
    Image apple;
    Timer timer;
    int x[]=new int[900];
    int y[]=new int[900];
    int dot_size=10;
    boolean up=false;
    boolean down=false;
    int count=1;
    boolean right=true;
    boolean left=false;
    Board(){
        super("Snake Game");
        addKeyListener(this);
        setBackground(Color.BLACK);
        setSize(300,300);
        setVisible(true);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("mypack/icons/head.png"));
        head=i1.getImage();
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("mypack/icons/dot.png"));
        dot=i2.getImage();
        timer=new Timer(100,this);
        timer.start();
        x[0]=100;
        y[0]=50;
    }
    public void paint(Graphics g){
        g.drawImage(head,x[0],y[0],this);
        for(int i=1;i<count;i++) {
            g.drawImage(dot, x[i], y[i], this);
        }
    }
    public void modifySnakeSnake(){


    }
    public void moveSnake(){
        if(right) {
            for (int i = 0; i <count; i++) {
                x[i] += 10;
            }
        }
        else if(left) {
            for (int i = 0; i < count; i++) {
                x[i] -= 10;
            }
        }
        else if(up){
                for (int i = 0; i <count; i++) {
                    y[i] -= 10;
                }
        }
        else {
            for (int i = 0; i <count; i++) {
                y[i] += 10;
            }
        }
    }
    public void actionPerformed(ActionEvent e){
        moveSnake();
        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
//        count++;
//        x[count-1]=x[count-2]-dot_size;
//        y[count-1]=50;
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_UP && down==false){
                up=true;
                right=false;
                left=false;
            }
            if(key==KeyEvent.VK_DOWN && up==false){
                down=true;
                right=false;
                left=false;
            }
            if(key==KeyEvent.VK_RIGHT && left==false){
                right=true;
                up=false;
                down=false;
            }
            if(key==KeyEvent.VK_LEFT && right==false){
                left=true;
                up=false;
                down=false;
            }
    }
    public void keyReleased(KeyEvent e){}

    public static void main(String[] args) {
        new Board();

    }


}



