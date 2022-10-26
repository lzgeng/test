package snake;

public class musicStart {
    public void initMusic1(){
        music1 bgm1=new music1("E:\\大二 Java\\贪吃蛇\\eight week snake\\src\\statics\\music.wav");
        bgm1.start();
    }

    public void initMusic2(){
        music2 bgm2=new music2("E:\\大二 Java\\贪吃蛇\\eight week snake\\src\\statics\\yinxiao.wav");
        bgm2.start();
    }

    public void initMusic3(){
        music2 bgm3=new music2("E:\\大二 Java\\贪吃蛇\\eight week snake\\src\\statics\\boom.wav");
        bgm3.start();
    }
    public void initMusic4(){
        music2 bgm4=new music2("E:\\大二 Java\\贪吃蛇\\eight week snake\\src\\statics\\土拨鼠.wav");
        bgm4.start();
    }
    public void initMusic5(){
        music2 bgm5=new music2("E:\\大二 Java\\贪吃蛇\\eight week snake\\src\\statics\\成龙.wav");
        bgm5.start();
    }
    public musicStart(){

    }
}
