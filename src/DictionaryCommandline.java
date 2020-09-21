public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.println("No  | English           | Vietnamese ");
        for (int i = 0; i < Words.size(); i++) {
            System.out.printf("%d\t| %-10s\t\t| %-10s\n",i+1,
                    Words.get(i).word_target,Words.get(i).word_explain);
        }
    }
    public void dictionaryBasic(){
        insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        DictionaryCommandline test = new DictionaryCommandline();
        test.dictionaryBasic();
    }

}