import java.util.*;

public class WordFrequencyGame {
    private final static String BLANK_SPACE = "\\s+";
    private final static String ERROR_MESSAGE = "Calculate Error";
    private final static String ONE = " 1";

    public String getResult(String message) {

        if (message.split(BLANK_SPACE).length == 1) {
            return message + ONE;
        } else {

            try {
                //split the input string with 1 to n pieces of spaces
                List<WordInfo> wordInfoList = new ArrayList<>();
                List<String> wordCount = new ArrayList(Arrays.asList(message.split(BLANK_SPACE)));

                buildWordInfo(wordInfoList, wordCount);

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map = getListMap(wordInfoList);

                wordInfoList = getWordInfos(map);

                wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : wordInfoList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
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
        } );
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }

        return map;
    }


}
