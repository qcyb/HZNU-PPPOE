import config.Config;
import CMD.cmdExe;
public class Dial {
    public static void main(String[] args) {
        String userName, password, connectionName;
        String command;
        Config cof = new Config();
        cmdExe cmd = new cmdExe();
        cof.run();
        connectionName = "PPPOE";
        userName = cof.getName();
        password = cof.getPassWord();
        command = "rasdial" + " " + connectionName + " " + userName + " " + password;
        cmd.runCommand(command);
    }
}