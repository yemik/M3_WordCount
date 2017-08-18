import java.io.*;

public class FileHandler implements FileHandling {
    private final String path;
    String inputLine;

    FileHandler(String path) {
        this.path = path;
    }

    public void readFile(File file) {
    }

    public String readLine(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File[] splitFile(File largeFile) {
        return new File[0];
    }

    public int findNoSplits(File largeFile) {
        return 0;
    }
}
