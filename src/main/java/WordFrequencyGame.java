import java.util.*;

public class WordFrequencyGame {
    private final static String BLANK_SPACE = "\\s+";
    private final static String ERROR_MESSAGE = "Calculate Error";
    private final static String ONE = " 1";
    private List<WordInfo> wordInfoList = new ArrayList<>();

    public String getResult(String message) {

        if (message.split(BLANK_SPACE).length == 1) {
            return message + ONE;
        } else {

            try {
                List<String> wordCount = new ArrayList(Arrays.asList(message.split(BLANK_SPACE)));
                generateWordInfo(wordCount);
                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");
                joinWordResult(wordInfoList, joiner);
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }

    private void generateWordInfo(List<String> wordCount) {
        buildWordInfo(wordInfoList, wordCount);
        Map<String, List<WordInfo>> map = getListMap(wordInfoList);
        wordInfoList = getWordInfos(map);
    }

    private void joinWordResult(List<WordInfo> wordInfoList, StringJoiner joiner) {
        wordInfoList.forEach(wordInfo -> {
            String s = wordInfo.getValue() + " " + wordInfo.getWordCount();
            joiner.add(s);
        });
    }

    private List<WordInfo> getWordInfos(Map<String, List<WordInfo>> map) {
        List<WordInfo> list = new ArrayList<>();

        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        return list;
    }

    private void buildWordInfo(List<WordInfo> wordInfoList, List<String> wordCount) {
        wordCount.forEach(s -> {
            WordInfo wordInfo = new WordInfo(s, 1);
            wordInfoList.add(wordInfo);
        });
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();

        wordInfoList.forEach(wordInfo -> {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        });
        return map;
    }


}
