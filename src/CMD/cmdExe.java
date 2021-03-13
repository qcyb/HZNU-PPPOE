package CMD;
public class cmdExe {
    public void runCommand(String command){
        try {
            Runtime.getRuntime().exec("cmd.exe /C start" + command);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Connecting!");
    }
}
