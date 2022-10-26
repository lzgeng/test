package snake;

import javax.swing.*;
import java.awt.*;

public class Snake extends JPanel{


    String fx;
    int lenth;
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];
    int score;
    boolean isStart = false;//游戏是否开始（空格键)
    boolean isFail = false;//定义一个死亡判断


    public Snake(){
        lenth = 3;
        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
        fx="R";//一开始往右
        score = 0;
        isStart = false;//游戏是否开始（空格键)
        isFail = false;//定义一个死亡判断
    }


    public void bodyMove(){
        for (int i = lenth-1; i > 0; i--) {//身体向前移动
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];    //后面的值赋给前一个
        }
    }
    public void move() {
        if (fx.equals("R")) {
            snakeX[0] = snakeX[0] + 25;//头部移动
            if (snakeX[0] > 1450) {
                snakeX[0] = 0;
            }//边界判断 不让小蛇消失 850为边框值
        } else if (fx.equals("L")) {
            snakeX[0] = snakeX[0] - 25;//头部移动
            if (snakeX[0] < 0) {
                snakeX[0] = 1450;
            }//边界判断
        } else if (fx.equals("U")) {
            snakeY[0] = snakeY[0] - 25;//头部移动
            if (snakeY[0] < 100) {
                snakeY[0] = 950;
            }//边界判断
        } else if (fx.equals("D")) {
            snakeY[0] = snakeY[0] + 25;//头部移动
            if (snakeY[0] > 950) {
                snakeY[0] = 100;
            }//边界判断
        }
    }

    public void death(){
        musicStart m = new musicStart();
        for (int i = 1; i < lenth; i++) {
            if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]) {
                m.initMusic4();
                isFail = true;
            }//自撞
        }
    }
}

