import config.Config;
public class Dial {
    public static void main(String[] args) {
        String userName, passWord;
        Config cf = new Config();
        userName = cf.getName();
        passWord = cf.getPassWord();
    }
}