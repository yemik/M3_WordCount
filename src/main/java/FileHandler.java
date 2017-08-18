import java.io.*;
import java.util.Scanner;

public class FileHandler implements FileHandling {
    private final String path;
    String inputLine;
    FileInputStream inputStream;
    Scanner streamer;

    FileHandler(String path) throws FileNotFoundException {
        this.path = path;
        inputStream = new FileInputStream(path);
        this.streamer = new Scanner(inputStream, "UTF-8");
    }

    public String streamLine() {
        if (streamer.hasNextLine()) {
            return streamer.nextLine();
        }
        return "";
    }

    @Override
    public void readFile(String filePath) {

    }

    @Override
    public String readLine(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String[] splitFile(String path) {
        return new String[0];
    }

    @Override
    public int findNoSplits(String largeFilePath) {
        return 0;
    }
}
