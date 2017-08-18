import java.io.File;
import java.util.HashMap;

public interface FileHandling {
    void readFile(File file);
    String readLine(File file);
    File[] splitFile(File largeFile);
    int findNoSplits(File largeFile);
}
