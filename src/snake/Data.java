package snake;

import javax.swing.*;
import java.net.URL;

public class Data {
    //头部
    public static URL headerURL = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //背景
    public static URL backgroundURL = Data.class.getResource("/statics/background.png");
    public static ImageIcon background = new ImageIcon(backgroundURL);

    //头部图片
    public static URL upUrl = Data.class.getResource("/statics/up1.png");
    public static URL downUrl = Data.class.getResource("/statics/down1.png");
    public static URL leftUrl = Data.class.getResource("/statics/left1.png");
    public static URL rightUrl = Data.class.getResource("/statics/right1.png");
    public static ImageIcon up =new ImageIcon(upUrl);
    public static ImageIcon down =new ImageIcon(downUrl);
    public static ImageIcon left =new ImageIcon(leftUrl);
    public static ImageIcon right =new ImageIcon(rightUrl);

    //身体
    public static URL bodyUrl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body =new ImageIcon(bodyUrl);

    //食物
    public static URL foodUrl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food =new ImageIcon(foodUrl);

    //障碍物
    public static URL ObstructionURL = Data.class.getResource("/statics/Obstruction.png");
    public static ImageIcon Obstruction =new ImageIcon(ObstructionURL);

    //无敌道具
    public static URL BestURL = Data.class.getResource("/statics/best.png");
    public static ImageIcon Best =new ImageIcon(BestURL);

    //炸弹
    public static URL BoomURL =Data.class.getResource("/statics/boom.png");
    public static ImageIcon Boom =new ImageIcon(BoomURL);
}
