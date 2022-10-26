package snake;
//定义一个循环播放的音效
import java.io.*;
import javax.sound.sampled.*;

public class music2 extends Thread {
    private String filename;
    private final int EXTERNAL_BUFFER_SIZE = 524288;

    public music2(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        File soundFile = new File(filename); // 播放音乐的文件名
        if (!soundFile.exists()) {//判断该文件是否存在，不存在则报错
            System.err.println("Wave file not found:" + filename);
            return;
        }
        AudioInputStream audioInputStream = null; // 创建音频输入流对象
        try {//处理异常
            audioInputStream = AudioSystem.getAudioInputStream(soundFile); // 创建音频对象，输入音频文件
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat(); // 音频格式
        SourceDataLine auline = null; // 源数据线
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
        }
        auline.start();//启动线程
        int nBytesRead = 0;//判断读取的字节数
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];//创建字节数组
        try {
            while (nBytesRead != -1) {//能够字节文件未读完
                nBytesRead = audioInputStream.read(abData, 0, abData.length);//读取文件字节
                if (nBytesRead >= 0)//若有读取字节，将字节写入源数据线
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
//auline.close();
        }
    }
}
