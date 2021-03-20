package CMD;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cmdExe {
    public String executeCommand(String command) throws Exception{
        System.out.println(command);
        Process cmdP = Runtime.getRuntime().exec("cmd /c " + command);
        System.out.println("正在尝试连接......");
        StringBuilder sbCmd = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(cmdP.getInputStream(), "GB2312"));
        String line;
        while ((line = br.readLine()) != null)  sbCmd.append((line + "\n"));
        return sbCmd.toString();
    }
}
