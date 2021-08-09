public class WordInfo {
    private final String value;
    private final int countWords;

    public WordInfo(String word, int countWords) {
        this.value = word;
        this.countWords = countWords;
    }


    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.countWords;
    }


}
