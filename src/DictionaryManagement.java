import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Scanner;
import java.io.IOException;


public class DictionaryManagement extends Dictionary{
    public static void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number words : ");
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            Word new_word = new Word();
            new_word.word_target = scanner.nextLine();
            new_word.word_explain = scanner.nextLine();
            Words.add(new_word);
        }
    }

    public static void insertFromFile() throws IOException {
        Scanner input = new Scanner(Paths.get("dictionaries.txt"),
                "UTF-8");
        while (input.hasNextLine()) {
            Word new_word = new Word();
            new_word.word_target = input.next();
            new_word.word_explain = input.nextLine();
            new_word.word_explain = new_word.word_explain.substring(1);
            Words.add(new_word);
        }
        input.close();
    }

    public static void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lookup : ");
        String s = scanner.nextLine();
        int count = 0;
        for (Word word : Words) {
            if (s.toLowerCase().equals(word.word_target.toLowerCase())) {
                System.out.println("Translate : " + word.word_explain);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found");
        }
    }

    public static void addNewWord() {
        Scanner scanner = new Scanner(System.in);
        Word new_word = new Word();
        new_word.word_target = scanner.nextLine();
        new_word.word_explain = scanner.nextLine();
        Words.add(new_word);
    }

    public static void fixWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Fix word : ");
        String s = scanner.next();
        for (Word word : Words) {
            if (word.word_target.toLowerCase().equals(s.toLowerCase())) {
                System.out.print("New word : ");
                word.word_target = scanner.next();
            }
        }
    }

    public static void removeWord() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        for (int i = 0; i < Words.size(); i++) {
            if (Words.get(i).word_target.toLowerCase().equals(s.toLowerCase())) {
                Words.remove(Words.get(i));
                break;
            }
        }
    }

    public static void dictionaryExportToFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter("output.txt", "UTF-8");
        Words.sort(Comparator.comparing(t -> t.word_target));
        printWriter.println("No      | English               | Vietnamese ");
        for (int i = 0; i < Words.size(); i++) {
            printWriter.printf("%d\t| %-10s\t\t| %-10s\n", i + 1,
                    Words.get(i).word_target, Words.get(i).word_explain);
        }
        printWriter.close();
    }

}