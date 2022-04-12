package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> wordsList = new ArrayList<>();
        Scanner scan = new Scanner(
                new File("C:\\Users\\Giorgi Chapidze\\IdeaProjects\\hangmanGame\\src\\com\\company\\hangmanWords.txt"));
        while(scan.hasNext()) {
            wordsList.add(scan.next());
        }
        Random rand = new Random();
        String word = wordsList.get(rand.nextInt(wordsList.size()));

        List<Character> playerGuesses = new ArrayList<>();
        System.out.println(word);
        int iWrong = 0;
        while (true) {

            displayUI(iWrong);
            if (iWrong == 7) {
                System.out.println("You Lose!");
                break;
            }

            if(!printGuessedState(word, playerGuesses)) {
                iWrong+=1;
            }
            if (printWordState(word.toLowerCase(), playerGuesses)) {
                break;
            }
            System.out.println("Please enter your guess for the word: ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.next().equals(word)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("Missed!(-_-) Try gain!");
            }
        }
    }

    private static void displayUI(int iWrong) {
        System.out.println(" -------- ");
        System.out.println(" |      |");
        if (iWrong >= 1) {
            System.out.println(" O");
        }
        if (iWrong >= 2) {
            System.out.print("\\ ");
            if (iWrong >= 3) {
                System.out.println("/");
            }
            else
                System.out.println("");
        }
        if (iWrong >= 4) {
            System.out.println(" |");
        }
        if(iWrong >= 5) {
            System.out.print("/ ");
            if (iWrong >= 6) {
                System.out.println("\\");
            }
            else
                System.out.println();
        }
    }

    private static boolean printWordState(String word, List<Character> playerGuess) {
        int iCount = 0;
        int i = -1;
        while(++i < word.length()) {
            if(playerGuess.contains(word.charAt(i))) {
                iCount++;
                System.out.print(word.charAt(i));
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();
        return (iCount == word.length());
    }
    private static boolean printGuessedState(String word,List<Character> playerGuesses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please type a letter: ");
        String playerGuess = scanner.nextLine();
        playerGuesses.add(playerGuess.charAt(0));

        return word.contains(playerGuess);
    }
}
