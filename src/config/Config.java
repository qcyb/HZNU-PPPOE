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
    String getName();
    String getPassWord();
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
    private static String rules;

    @Override
    public String getName() { return username + rules;}
    @Override
    public String getPassWord() { return password; }
    @Override
    public void chooseRules() {
        //初始化程序
        props.setProperty("configured", "true");
        System.out.println("接下来是第一次运行的初始化内容");
        System.out.println("请选择你的运营商店（输入数字即可）");
        System.out.println("1.电信 2.移动 3.联通");
        int operator = keyIn.nextInt();
        keyIn.nextLine();

        //得到运行商的后缀
        if (operator == 1) rules = props.getProperty("CT");
        if (operator == 2) rules = props.getProperty("CM");
        if (operator == 3) rules = props.getProperty("CU");

        props.setProperty("rules", rules);
    }


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


    public void run() throws Exception{

        //创建和连接配置文件
        String fileError = "NULL";
        Config conf = new Config();
        String path = "HZNU-PPPOE\\src\\config\\config.properties";
        File file = new File(path);

        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            fileError = "配置文件丢失！";
            System.out.println(fileError);
            System.exit(1);
        }

        if(props.getProperty("configured").equals("false")){
            conf.chooseRules();
            conf.setUserName();
        }
        conf.setPassWord(); //Set password anyway ^<>^.

        //将上述读取的信息写入配置文件
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
        props.store(writer, "Finishing initialization.");
    }

}
