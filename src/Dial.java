import config.Config;
import CMD.cmdExe;
public class Dial {
    public static void main(String[] args) throws Exception{
        String userName, password, connectionName;
        String command, retString;
        Config cof = new Config();
        cmdExe cmd = new cmdExe();
        cof.run();
        userName = cof.getName();
        password = cof.getPassWord();
        connectionName = "PPPOE";
        command = "rasdial " + connectionName + " " + userName + " " + password;
        retString = cmd.executeCommand(command);
        if(retString.indexOf("已连接") > 0) System.out.println("已连接");
        else                               System.out.println("未连接");
        System.out.println("错误信息如下:");
        System.out.println(retString);
    }
}