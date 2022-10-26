package snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args){
        JFrame frame = new JFrame("植物大战僵尸版贪吃蛇");
        frame.setBounds(150,15,1500,1025);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new GamePanel());

        frame.setVisible(true);
        GamePanel gPanel =new GamePanel();//                          问1 在这里初始化 music2不用 放在music包里可否
        gPanel.m.initMusic1();
    }
}
