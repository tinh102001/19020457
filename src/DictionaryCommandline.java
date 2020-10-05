import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public static void showAllWords() {
        Words.sort(Comparator.comparing(t -> t.word_target));
        System.out.println("No  | English           | Vietnamese ");
        for (int i = 0; i < Words.size(); i++) {
            System.out.printf("%d\t| %-10s\t\t| %-10s\n", i + 1,
                    Words.get(i).word_target, Words.get(i).word_explain);
        }
    }

    public static void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced() throws IOException {
        insertFromFile();
        showAllWords();
        dictionaryLookup();
    }

    public static void dictionarySearcher(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search : ");
        String s = scanner.nextLine();
        for (int i = 0; i < Words.size(); i++) {
            if (Words.get(i).word_target.toLowerCase().contains(s.toLowerCase())) {
                System.out.printf("%d\t| %-10s\t\t| %-10s\n", i + 1,
                        Words.get(i).word_target, Words.get(i).word_explain);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean cont = true;
        do {
            System.out.println("        TEST        ");
            System.out.println("1 - Dictionary Basic");
            System.out.println("2 - Dictionary Advanced");
            System.out.println("3 - Add New word");
            System.out.println("4 - Remove");
            System.out.println("5 - Fix");
            System.out.println("6 - Show Dictionary");
            System.out.println("7 - Show Dictionary From File");
            System.out.println("8 - Exit");
            System.out.print("Your selection : ");
            int chon = input.nextInt();
            switch (chon) {
                case 1:
                    System.out.println("DictionaryBasic");
                    dictionaryBasic();
                    dictionarySearcher();
                    break;
                case 2:
                    System.out.println("DictionaryAdvanced");
                    dictionaryAdvanced();
                    dictionarySearcher();
                    break;
                case 3:
                    System.out.print("AddNewWord :");
                    addNewWord();
                    break;
                case 4:
                    System.out.print("Remove :");
                    removeWord();
                    break;
                case 5:
                    fixWord();
                    break;
                case 6:
                    showAllWords();
                    break;
                case 7:
                    dictionaryExportToFile();
                    break;
                case 8:
                    System.out.println("Bye");
                    cont = false;
                    break;
                default:
                    System.out.println("You didn't select ");
                    break;
            }
        } while (cont);
    }

}