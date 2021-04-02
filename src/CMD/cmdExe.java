package CMD;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cmdExe {
    public String executeCommand(String command) throws Exception{
        //执行从主函数Dial传入的cmd命令
        Process cmdP = Runtime.getRuntime().exec("cmd /c " + command);
        System.out.println("......");

        //将cmd的执行结果返回主函数
        StringBuilder sbCmd = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(cmdP.getInputStream(), "GB2312"));
        String line;
        while ((line = br.readLine()) != null)  sbCmd.append((line + "\n"));
        return sbCmd.toString();
    }
}
