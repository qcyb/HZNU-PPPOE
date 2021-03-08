/*
* project: PPPOE
* filename: Config.java
* src: src/config/Config.java
* description: 配置文件的读取和应用变量的配置
* */
package config;

import java.util.Properties;
import java.io.*;

/*
* description: 需要实现的接口定义
* getName: 返回真实用户名
* getPassWord: 返回密码
* */
interface method{
    String getName();
    String getPassWord();
    String getConfigured();
}

/*
* description: 线程通讯问题
*  */
class judgeThread implements Runnable{
    Thread thrd;
    String sentence;
    String legalChar = "0123456789@ctmu";

    judgeThread(String sentence){
        this.sentence = sentence;
        thrd = new Thread(this, "#judgeThread");
        thrd.start();
    }

    /*
    * description: 判断合法性
    * */
    @Override
    public void run(){
        int i = 0;
        for(i = 0; i < sentence.length(); i ++)
            if (!(legalChar.contains( String.valueOf(sentence.charAt(i) )))) break;
        if(i != sentence.length())  System.out.println("存在非法字符！");
    }
}

public class Config implements method{

    /*
     * description: 配置文件读取用户基本信息、应用基本信息
     * load-from: setting.properties
     * */
    private static String username;
    private static String password;
    private static String configured;//判断是否已初始化

    @Override
    public String getName() { return username + "@ctc"; }
    @Override
    public String getPassWord() { return password; }
    @Override
    public String getConfigured() { return configured; }

    public static void main(String[] args) {

        /*
        * description: 错误类型定义
        * fileError: 文件读取过程中出错
        * typeError: 用户名或者密码中出现非法字符
        * */
        String fileError = "NULL", typeError = "NULL";

        String path = "src\\config\\config.properties";
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            fileError = "配置文件丢失！";
            System.exit(1);
        }

        username = props.getProperty("username");
        password = props.getProperty("password");
        configured = props.getProperty("configured");
    }
}
