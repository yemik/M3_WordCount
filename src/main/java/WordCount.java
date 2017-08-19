import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface WordCount {
    Map<String, Integer> sortFile(String path);
    void addWords(String[] words);
    Map<String, Integer> aggregateCounts(Map<String, Integer> wordCounts);
    Map<String, Integer> getTopWords(Map<String, Integer> wordCounts, int noOfFrequentWords);
    void outputResults();
}