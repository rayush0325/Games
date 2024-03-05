package myPack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//here we declared Board as panel so as to add its code to the frame
public class Board extends Frame implements ActionListener,KeyListener {
    static Image head;
    static Image dot;
    Image apple;
    Timer timer;
    int x[]=new int[900];
    int y[]=new int[900];
    int snake_size=3;
    int ax,ay;
    ArrayList<Info> arr;
    int dot_size=10;
    Board(){
        super("Snake Game");
        addKeyListener(this);
        setBackground(Color.BLACK);
        setSize(310,310);
        setVisible(true);
        setLocationRelativeTo(this);
        loadImage();
        timer=new Timer(100,this);
        createSnake();
        arr=new ArrayList<>();
        Info obj=new Info();
        obj.direction="right";
        obj.iterations=snake_size;
        arr.add(obj);
        drawApple();
    }
    public  void loadImage(){
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("mypack/icons/head.png"));
        head=i1.getImage();
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("mypack/icons/dot.png"));
        dot=i2.getImage();
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("mypack/icons/apple.png"));
        apple=i3.getImage();
    }
    public void paint(Graphics g){
        g.drawImage(apple,ax,ay,this);
        g.drawImage(head,x[0],y[0],this);
        for(int i=1;i<snake_size;i++) {
            g.drawImage(dot, x[i], y[i], this);
        }
    }
    public void drawApple(){
        ax=((int)(Math.random()*10)+10)*10;
        ay=((int)(Math.random()*10)+10)*10;
    }
    public void createSnake(){
        for(int i=0;i<snake_size;i++){
            x[i]=100-i*dot_size;
            y[i]=50;
        }
    }
    public void moveSnake(){
        int k=0;
        while(k<snake_size) {
            for (int i = arr.size() - 1; i >= 0; i--) {
                for (int j = 0; j < arr.get(i).iterations; j++) {
                    switch (arr.get(i).direction) {
                        case "up":
                            y[k] -= dot_size;
                            break;
                        case "down":
                            y[k] += dot_size;
                            break;
                        case "right":
                            x[k] += dot_size;
                            break;
                        case "left":
                            x[k] -= dot_size;
                    }
                    k++;
                }
            }
        }
        if(arr.size()>1){
            arr.get(0).iterations-=1;
            arr.get(arr.size()-1).iterations+=1;
        }
        if(arr.get(0).iterations==0){
            arr.remove(0);
        }
    }
    public void actionPerformed(ActionEvent e){
        if(ax==x[0] && ay==y[0]){
            switch(arr.get(0).direction){
                case "up":y[snake_size]=y[snake_size-1]+dot_size;
                    x[snake_size]=x[snake_size-1];
                    break;
                case "down":y[snake_size]=y[snake_size-1]-dot_size;
                    x[snake_size]=x[snake_size-1];
                    break;
                case "right":x[snake_size]=x[snake_size-1]-dot_size;
                    y[snake_size]=y[snake_size-1];
                    break;
                case "left":x[snake_size]=x[snake_size-1]+dot_size;
                    y[snake_size]=y[snake_size-1];
                    break;
            }
            snake_size++;
            arr.get(0).iterations+=1;
            drawApple();
        }
       for(int i=1;i<snake_size;i++){
           if((x[0]==x[i] && y[0]==y[i])||!(x[0]>20 && x[0]<300)||!(y[0]>20 && y[0]<300)){
               timer.stop();
           }
       }
        moveSnake();
        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
            Info obj=new Info();
            if (key == KeyEvent.VK_UP) {
                obj.direction = "up";
                obj.opposite = "down";
            }
            if (key == KeyEvent.VK_DOWN) {
                obj.direction = "down";
                obj.opposite = "up";
            }
            if (key == KeyEvent.VK_RIGHT) {
                obj.direction = "right";
                obj.opposite = "left";
            }
            if (key == KeyEvent.VK_LEFT) {
                obj.direction = "left";
                obj.opposite = "right";
            }
           if (arr.get(arr.size() - 1).opposite != (obj.direction)) {
                arr.add(obj);
            }
            timer.start();
    }
    public void keyReleased(KeyEvent e){}
    public static void main(String[] args) {
        new Board();
    }
}
class Info{
    String direction, opposite;
    int iterations;
}



