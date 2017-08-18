import java.io.File;
import java.util.HashMap;

public interface FileHandling {
    String path = "";
    void readFile(File file);
    String readLine(File file);
    File[] splitFile(File largeFile);
    int findNoSplits(File largeFile);
}
