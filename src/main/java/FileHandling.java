import java.io.File;
import java.util.HashMap;

public interface FileHandling {
    String path = "";
    void readFile(String filePath);
    String readLine(String filePath);
    String[] splitFile(String largefilePath);
    int findNoSplits(String largeFilePath);
}
