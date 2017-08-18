import java.io.File;
import java.util.HashMap;

public interface WordCount {
    HashMap<String, Integer> sortFile(File file);
    HashMap<String, Integer> addWords(File file);
    HashMap<String, Integer> aggregateCounts(HashMap<String, Integer> wordCounts);
    HashMap<String, Integer> getTopWords(HashMap<String, Integer> wordCounts, int noOfFrequentWords);
    void outputResults(HashMap<String, Integer> wordCounts);
}