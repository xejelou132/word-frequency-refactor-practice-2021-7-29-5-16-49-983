import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.frequency;
import static java.util.stream.Collectors.toList;

public class WordFrequencyGame {
    private final static String BLANK_SPACE = "\\s+";
    private final static String ERROR_MESSAGE = "Calculate Error";
    private final static String ONE = " 1";
    private List<WordInfo> wordInfo = new ArrayList<>();

    public String getResult(String sentence) {
        if (sentence.split(BLANK_SPACE).length == 1) {
            return sentence.concat(ONE);
        }
        try {
            wordInfo = calculateWordInfo(sentence);
            return joinWords(wordInfo);
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    private String joinWords(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo1 -> wordInfo1.getValue() + " " + wordInfo1.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private List<WordInfo> calculateWordInfo(String sentence) {
        List<String> words = asList(sentence.split(BLANK_SPACE)); // the the is
        List<String> distinctWord = words.stream().distinct().collect(toList()); // the is

        return distinctWord.stream()
                .map(word -> new WordInfo(word, frequency(words, word)))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(toList());
    }
}
