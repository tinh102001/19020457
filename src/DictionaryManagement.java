import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.nextLine();
        for (int i = 0; i < n; i++) {
            Word new_word = new Word();
            new_word.word_target = scanner.nextLine();
            new_word.word_explain = scanner.nextLine();
            Words.add(new_word);
        }
    }
}