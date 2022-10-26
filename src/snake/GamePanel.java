package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;



public class GamePanel extends JPanel implements KeyListener, ActionListener {//继承JPanel属性 面板，可把它加到frame窗体里
    //Key:监听键盘 ，要重写方法（implement method）会在下方生成

    Snake snake = new Snake();
    Food food = new Food();
    Obstructions obstructions = new Obstructions();
    Boom boom = new Boom();
    Best best = new Best();
    Draw draw = new Draw();
    Random random = new Random();                  //食物坐标有关
    musicStart m = new musicStart();
    public void start(){
        m.initMusic1();
        m.initMusic2();
        m.initMusic3();
        m.initMusic4();
        m.initMusic5();
    }

//    boolean isStart = false;//游戏是否开始（空格键)
//    //定义一个死亡判断
//    boolean isFail = false;

    Timer timer = new Timer(100, this);//定时器 每秒10帧 100毫秒（调整小蛇速度，越大越慢）/ 监听当前界面


    //构造器
    public GamePanel(){
        //init();                      ////////////////////////////////////问2 初始化写蛇里
        //获取键盘的监听事件
        this.setFocusable(true);//获取键盘交点，设定为真 聚集在游戏上
        this.addKeyListener(this);//添加监听类（第10行）括号里代表获取当前键
        //this 点击more—— cat下边，会在第10行生成ActionListener
        timer.start(); //让时间流动 计时器 actionPerformed方法内
    }


//    public void draw(Graphics g){                           问6 分draw包写 蛇不动
//        draw.paintComponent(g);
//    }

    //画板：画界面，画蛇
    @Override                   //重写赋类方法：Generate /Override Methods /paintComponent(绘制界面)
    protected void paintComponent(Graphics g) {//Graphics:画笔
        super.paintComponent(g);               //清屏
        this.setBackground(Color.BLACK);       //设置背景颜色

        //draw.paintComponent(g);                //只能画静态

        //绘制头部
        Data.header.paintIcon(this,g,0,0);//this:画到这里 g:画笔 离边框位置
        //绘制游戏区域
        g.fillRect(0,75,1500,1000);//fill（矩形）25 850 600
        Data.background.paintIcon(this,g,0,75);

        //画头部
        switch (snake.fx) {
            case "R":          //fx为方向，equal为值
                Data.right.paintIcon(this, g, snake.snakeX[0], snake.snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snake.snakeX[0], snake.snakeY[0]);
                break;
            case "U":
                Data.up.paintIcon(this, g, snake.snakeX[0], snake.snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snake.snakeX[0], snake.snakeY[0]);
                break;
        }
        for (int i = 1; i < snake.lenth; i++) {
            Data.body.paintIcon(this,g,snake.snakeX[i],snake.snakeY[i]);//蛇身体长度通过length控制
        }


        //画食物
        for (int i = 0; i <5 ; i++) {
            Data.food.paintIcon(this,g,food.foodX[i],food.foodY[i]);
        }

        //画障碍物
        for (int i = 0; i < 20; i++) {
            Data.Obstruction.paintIcon(this,g,obstructions.ObstructionsX[i],obstructions.ObstructionsY[i]);
        }

        //画无敌道具
        if(snake.lenth%5==0){
            Data.Best.paintIcon(this,g,best.bestX,best.bestY);
        }

        //画炸弹
        for (int i = 0; i <5; i++) {
            Data.Boom.paintIcon(this,g,boom.boomX[i],boom.boomY[i]);
        }

        //游戏提示：是否开始
        if(!snake.isStart){
            //画一个文字 String
            g.setColor(Color.WHITE);//设置画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体 加粗 字体大小
            g.drawString("按下空格开始游戏",550,500);//写一个字符串
        }

        //失败提示
        if(snake.isFail){
            //画一个文字 String
            g.setColor(Color.RED);//设置画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,100));//设置字体 加粗 字体大小
            g.drawString("按下空格重新开始",450,500);//写一个字符串
        }


    }

    //监听键盘
    @Override
    public void keyPressed(KeyEvent e) {    //由第10行而来 键盘事件
        //键盘按下，未释放
        //获取按下的键盘是那个键
        int keycode = e.getKeyCode();//定义一个变量由keycode方法得到键盘输入键

        if(keycode==KeyEvent.VK_SPACE){//判断按下的是否为空格键
            if(snake.isFail){                //失败，游戏重来
                snake.isFail = false;
                snake = new Snake();
                food = new Food();
                obstructions = new Obstructions();
                boom = new Boom();
                best = new Best();
                //init();                                                              //初始化
            }else{                     //暂停游戏
                snake.isStart = !snake.isStart;//取反 按下空格开始或暂停
            }
            repaint();//重新绘制界面 刷新界面
        }
        //控制键盘走向
        if(keycode==KeyEvent.VK_A && snake.fx!="R"){
            snake.fx = "L";
        }else if(keycode==KeyEvent.VK_D && snake.fx!="L"){
            snake.fx = "R";
        }else if(keycode==KeyEvent.VK_W && snake.fx!="D"){
            snake.fx = "U";
        }else if(keycode==KeyEvent.VK_S && snake.fx!="U"){
            snake.fx = "D";
        }
    }

    //定时器 监听时间 帧 按下空格后小蛇会动
    @Override       //执行 定时操作 ！！！
    public void actionPerformed (ActionEvent e) {
        //如果游戏处于开始状态，且游戏未结束
        if(snake.isStart && snake.isFail == false){
            //右移
            snake.bodyMove();
            //通过控制方向让头部移动
            snake.move();

            //吃食物
            food.eatFood(snake,food,m);

            //小蛇撞到障碍物 会掉3格身体
            obstructions.bumpObs(snake,obstructions,food,m);

//            //小蛇吃到无敌药水，可以连续撞三个障碍物
            best.bumpBest(snake,food,best,m);

//            //如果小蛇撞到炸弹，则直接死亡
            boom.bumpBoom(snake,food,boom,m);

             //结束判断,上面是蛇长度小于等于0游戏结束，这里是蛇咬到自身游戏结束
            snake.death();
            repaint();
        }

        timer.start();//让时间流动 启动计时器

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起 敲击
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }
}
