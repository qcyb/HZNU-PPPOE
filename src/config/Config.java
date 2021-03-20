/*
* project: PPPOE
* filename: Config.java
* src: src/config/Config.java
* description: 配置文件的读取和应用变量的配置
* */
package config;

import java.util.Properties;
import java.io.*;
import java.util.Scanner;

/*
* description: 需要实现的接口定义
* getName: 返回真实用户名
* getPassWord: 返回密码
* */
interface method{
    void chooseRules();
    void setUserName();
    void setPassWord();
    void setRules();
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
     * description: 配置和读取用户基本信息、应用基本信息
     * load-from: setting.properties
     * */
    private static final Scanner keyIn = new Scanner(System.in);
    public static final Properties props = new Properties();
    public static String username;
    public static String password;
    private static String configured;//判断是否已初始化
    private static int Operator;     //选择的运营商
    private static String rules;

    @Override
    public String getName() { return username + rules;}
    @Override
    public String getPassWord() { return password; }
    @Override
    public String getConfigured() { return configured; }
    @Override
    public void chooseRules() {
        props.setProperty("configured", "true");
        System.out.println("接下来是第一次运行的初始化内容");
        System.out.println("请选择你的运营商店（输入数字即可）");
        System.out.println("""
                1.电信
                2.移动
                3.联通""");
        Operator = keyIn.nextInt();
        keyIn.nextLine();
        if (Operator == 1) rules = props.getProperty("CT");
        if (Operator == 2) rules = props.getProperty("CM");
        if (Operator == 3) rules = props.getProperty("CU");
    }

    @Override
    public void setRules() { props.setProperty("rules", rules); }

    @Override
    public void setUserName() {
        System.out.println("请输入学号:");
        username = keyIn.nextLine();
        props.setProperty("username", username);
    }
    @Override
    public void setPassWord() {
        System.out.println("请输入密码:");
        password = keyIn.nextLine();
        props.setProperty("password", password);
    }


    public void run(){
        /*
         * description: 错误类型定义
         * fileError: 文件读取过程中出错
         * typeError: 用户名或者密码中出现非法字符
         * */
        String fileError = "NULL", typeError = "NULL";
        Config conf = new Config();
        String path = "src\\config\\config.properties";
        File file = new File(path);
        try {
//            props.load(Config.class.getResourceAsStream("config.properties"));
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            fileError = "配置文件丢失！";
            System.out.println(fileError);
            System.exit(1);
        }

        username = props.getProperty("username");
        password = props.getProperty("password");
        configured = props.getProperty("configured");

        if(configured.equals("false")){
            conf.chooseRules();
            conf.setRules();
            conf.setUserName();
        }
        conf.setPassWord();
        rules = props.getProperty("rules");
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            props.store(writer, "Finishing initialization.");
        }catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}
