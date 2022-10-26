package snake;

import java.io.File;
import java.io.IOException;
//循环播放背景音乐
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


//定义一个循环播放音乐的类，并继承多线程
public class music1 extends Thread {

    private String fileName;
    private final int EXTERNAL_BUFFER_SIZE = 524288;

    public music1(String wavFile) {
        this.fileName = wavFile;
    }

    @SuppressWarnings("unused")
    public void run() {

        File soundFile = new File(fileName); // 播放音乐的文件名
        if (!soundFile.exists()) {//判断该文件是否存在，不存在则报错
            System.err.println("Wave file not found:" + fileName);
            return;
        }
        while (true) { // 设置循环播放
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
// auline.close();
            }
        }

    }

}