import java.util.Random;
import java.util.Scanner;

public class Main {
    static String[] words = {"hello", "words", "test"};
    static String target;
    static StringBuilder userGuess;

    public static void main(String[] args) {
        // Variables
        Scanner scanner = new Scanner(System.in);
        target = returnNewWord();
        userGuess = createUserGuess(target);

        welcome();
        int turns = scanner.nextInt();

        while (turns > 0) {
            System.out.println("You have " + turns + " left.");
            System.out.println("Here's your current guess: " + userGuess);
            System.out.println("Would you like to guess a letter or a word? (w for word, l for letter)");

            char choice = scanner.next().charAt(0);

            switch (choice) {
                case 'l':
                    System.out.println("Enter your letter");
                    char letter = scanner.next().toLowerCase().charAt(0);
                    checkLetter(letter);
                    break;
                case 'w':
                    System.out.println("Enter your word");
                    String word = scanner.next();
                    checkWord(word);
                    break;
                default:
                    System.out.println("Error, Try Again!");
                    break;
            }

            if (userGuess.toString().equals(target)) {
                System.out.println("You Win!\nYou had " + turns + " turns left\nGood Job!");
                break;
            }

            turns--;
        }

        if (turns == 0 && !(userGuess.toString().equals(target))) {
            System.out.println("You Lose!\nThe correct word was: " + target + "\nTry Again!");
        }

        scanner.close();
    }

    public static void welcome() {
        System.out.println("Welcome to Hangman!");
        System.out.println("How many turns would you like?");
    }

    public static String returnNewWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public static StringBuilder createUserGuess(String word) {
        String str = "";

        for (int i = 0; i < word.length(); i++) {
            str += "_";
        }

        return new StringBuilder(str);
    }

    public static void checkLetter(char letter) {
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == letter) {
                userGuess.setCharAt(i, letter);
            }
        }
    }

    public static void checkWord(String word) {
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == word.charAt(i)) {
                char letter = target.charAt(i);
                userGuess.setCharAt(i, letter);
            }
        }
    }
}