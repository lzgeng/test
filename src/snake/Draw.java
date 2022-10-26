package snake;

import javax.swing.*;
import java.awt.*;

public class Draw extends Snake {

    public Draw(){

    }

    //画板：画界面，画蛇
    @Override                   //重写赋类方法：Generate /Override Methods /paintComponent(绘制界面)
    protected void paintComponent(Graphics g) {//Graphics:画笔

        //绘制头部
        Data.header.paintIcon(this,g,0,0);//this:画到这里 g:画笔 离边框位置
        //绘制游戏区域
        g.fillRect(0,75,1500,1000);//fill（矩形）25 850 600
        Data.background.paintIcon(this,g,0,75);

//        //画头部
//        //draw.paintComponent(g);
//        switch (fx) {
//            case "R":          //fx为方向，equal为值
//                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
//                break;
//            case "L":
//                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
//                break;
//            case "U":
//                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
//                break;
//            case "D":
//                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
//                break;
//        }
//        for (int i = 1; i < lenth; i++) {
//            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);//蛇身体长度通过length控制
//        }

//        //画食物
//        for (int i = 0; i <5 ; i++) {
//            Data.food.paintIcon(this,g,food.foodX[i],food.foodY[i]);
//        }
//
//        //游戏提示：是否开始
//        if(!isStart){
//            //画一个文字 String
//            g.setColor(Color.WHITE);//设置画笔颜色
//            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体 加粗 字体大小
//            g.drawString("按下空格开始游戏",550,500);//写一个字符串
//        }
//
//        //失败提示
//        if(isFail){
//            //画一个文字 String
//            g.setColor(Color.RED);//设置画笔颜色
//            g.setFont(new Font("微软雅黑",Font.BOLD,100));//设置字体 加粗 字体大小
//            g.drawString("按下空格重新开始",450,500);//写一个字符串
//        }


    }
}
