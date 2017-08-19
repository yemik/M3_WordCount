import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileHandler wordCountFile = new FileHandler("resources/aSample2.txt");
        wordCountFile.readFile();
        wordCountFile.displayWordCounts();
    }
}
