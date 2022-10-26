package snake;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;

public class Food extends Snake  {
    Random random = new Random();                  //食物坐标有关
    int[] foodX=new int[50];
    int[] foodY=new int[50];
    int[] ObstructionsX=new int[50] ;              //障碍坐标
    int[] ObstructionsY=new int[50] ;
    int[] boomX=new int[50];                        //炸弹
    int[] boomY=new int[50];
    int bestX=0;                                   //无敌药水
    int bestY=0;
    int lizhix,k;


    public int getX(){
        int x = 100 + 25 * random.nextInt(50);
        boolean isFlag = true;
        while (isFlag) {
            for (int i = 0; i < 50; i++) {                        //50
                if (snakeX[i] == x || ObstructionsX[i] == x || bestX == x || boomX[i] == x ) {                             //问5 解决重合 一层层判断

                    x = 100 + 25 * random.nextInt(50);

                } else {
                    isFlag = false;
                }

            }
        }

        return x;
    }

    public int getY() {
        int y = 100 + 25 * random.nextInt(35);
        boolean isFlag = true;
        while (isFlag) {
            for (int i = 0; i < 50; i++) {
                if (snakeY[i] == y || ObstructionsY[i] == y || bestY == y || boomY[i] == y) {

                    y = 100 + 25 * random.nextInt(35);

                } else {
                    isFlag = false;
                }

            }
        }

        return y;
    }

    public Food(){
        for (int i = 0; i <5 ; i++) {
            foodX[i] = getX();                                  //问4 随机生成食物坐标 重写
            foodY[i] = getY();
        }
    }

    public void eatFood(Snake snake,Food food,musicStart m) {                                       //问7 为何不行
        for (int i = 0; i <5; i++) {
            if(snake.snakeX[0]==food.foodX[i] && snake.snakeY[0]==food.foodY[i]){
                m.initMusic2();
                //长度+1
                snake.lenth++;
                snake.snakeX[snake.lenth-1]=9999;
                snake.snakeY[snake.lenth-1]=9999;
                snake.score = snake.score + 10;//加分数
                //重新生成食物
                food.foodX[i] = getX();
                food.foodY[i] = getY();
            }
        }
    }

}

class Obstructions extends Food{
    public Obstructions(){
        for(int i = 0; i < 20; i++){
            ObstructionsX[i] = getX();
            ObstructionsY[i] = getY();
        }
    }
    //小蛇撞到障碍物 会掉3格身体
    public void bumpObs(Snake snake,Obstructions obstructions,Food food,musicStart m){
        //GamePanel g = new GamePanel();
        for (int i = 0; i < obstructions.ObstructionsX.length; i++) {
            //initMusic2();
            if(snake.snakeX[0]==obstructions.ObstructionsX[i]&&snake.snakeY[0]==obstructions.ObstructionsY[i]){
                m.initMusic5();
                if(food.k==1) {//无敌药效时
                    if(food.lizhix!=0) {
                        food.lizhix--;
                        snake.isFail = false;
                        snake.score += 10;
                        obstructions.ObstructionsX[i] = food.getX();
                        obstructions.ObstructionsY[i] = food.getY();
                    }
                    else {
                        m.initMusic5();
                        food.k=0;
                        snake.lenth=snake.lenth-3;
                        snake.score=snake.score-30;
                        obstructions.ObstructionsX[i] = food.getX();
                        obstructions.ObstructionsY[i] = food.getY();
                    }
                }

                else{
                    snake.lenth=snake.lenth-3;
                    snake.score=snake.score-30;
                    obstructions.ObstructionsX[i] = food.getX();
                    obstructions.ObstructionsY[i] = food.getY();
                }

            }

            if(snake.lenth<=0){
                snake.isFail=true;
            }
        }
    }
}

class Boom extends Food{
    public Boom(){
        for(int i = 0; i < 5; i++){
            boomX[i] = getX();
            boomY[i] = getY();
        }
    }
    public void bumpBoom(Snake snake,Food food,Boom boom,musicStart m) {
        for (int i = 0; i < boom.boomX.length; i++) {
            if (snake.snakeX[0] == boom.boomX[i] && snake.snakeY[i] == boom.boomY[i]) {
                m.initMusic3();
                if (food.k == 1 && food.lizhix != 0) {
                    food.lizhix = 0;//可以吃到一次炸弹
                    food.k = 0;
                    boom.boomX[i] = food.getX();
                    boom.boomY[i] = food.getY();
                } else {
                    snake.isFail = true;
                }

            }
        }
    }
}

class Best extends Food{
    public Best(){
        bestX = getX();
        bestY = getY();
    }
    public void bumpBest(Snake snake,Food food,Best best,musicStart m){
        if(snake.snakeX[0]==best.bestX&&snake.snakeY[0]==best.bestY){
            food.k=1;
            food.lizhix=3;
            m.initMusic2();
            snake.lenth++;
            snake.snakeX[snake.lenth-1]=snake.snakeX[snake.lenth-2];//解决闪烁问题
            snake.snakeY[snake.lenth-1]=snake.snakeY[snake.lenth-2];
            best.bestX=food.getX();
            best.bestY=food.getY();
        }
    }
}