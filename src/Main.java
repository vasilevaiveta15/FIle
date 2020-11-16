import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String path = checkPath(scanner);
        String[][] matrixWithAllWords = fillArray(scanner, path);

        System.out.print("What command do you want: (0) to switch two lines, (1) to switch two words or (end) to stop program: ");
        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("End")) {
            if (command.equalsIgnoreCase("0")) {

                switchTwoLines(scanner, matrixWithAllWords, path);

            } else if (command.equalsIgnoreCase("1")) {

                switchWord(matrixWithAllWords, scanner, path);

            }
            System.out.print("What command do you want: (0) to switch two lines, (1) to switch two words or (end) to stop program: ");
            command = scanner.nextLine();
        }
    }

    private static void switchWord(String[][] matrixWithAllWords, Scanner scanner, String path) throws FileNotFoundException {
        System.out.print("Enter first word position(row and col):\n");
        System.out.print("Row of first word: ");
        int rowOfFirstWord = Integer.parseInt(scanner.nextLine());
        System.out.print("Col of first word: ");
        int colOfFirstWord = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter second word:\n");
        System.out.print("Row of second word: ");
        int rowOfSecondWord = Integer.parseInt(scanner.nextLine());
        System.out.print("Col of second word: ");
        int colOfSecondWord = Integer.parseInt(scanner.nextLine());


        String word1Text = matrixWithAllWords[rowOfFirstWord][colOfFirstWord];
        String word2Text = matrixWithAllWords[rowOfSecondWord][colOfSecondWord];

        matrixWithAllWords[rowOfFirstWord][colOfFirstWord] = word2Text;
        matrixWithAllWords[rowOfSecondWord][colOfSecondWord] = word1Text;
        System.out.println("You successful switch two words! Congratulations!!!");

        writeToFile(path, matrixWithAllWords);
    }

    private static void switchTwoLines(Scanner scanner, String[][] matrixWithAllWords, String path) throws FileNotFoundException {
        System.out.print("Enter first row: ");
        int row1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter second row: ");
        int row2 = Integer.parseInt(scanner.nextLine());
        if (row1 > matrixWithAllWords.length - 1 || row2 > matrixWithAllWords.length - 1 || row1 < 0 || row2 < 0) {
            System.out.println("You enter invalid row number.");
        } else {
            String[] row1Text = matrixWithAllWords[row1];
            String[] row2Text = matrixWithAllWords[row2];
            matrixWithAllWords[row1] = row2Text;
            matrixWithAllWords[row2] = row1Text;
            System.out.println("You successful switch two line! Congratulations!!!");
        }
        writeToFile(path, matrixWithAllWords);
    }

    private static void writeToFile(String path, String[][] matrixWithAllWords) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(path);
        for (int i = 0; i < matrixWithAllWords.length; i++) {
            for (int j = 0; j < matrixWithAllWords[i].length; j++) {
                writer.print(matrixWithAllWords[i][j] + " ");
            }
            writer.println();
        }
        writer.close();
    }

    private static String checkPath(Scanner scanner) {
        while (true) {
            System.out.print("Enter your path which you want to save file: ");
            String path = scanner.nextLine();
            try {
                List<String> lines = Files.readAllLines(Path.of(path));
                return path;
            } catch (IOException e) {
                System.out.println("You enter invalid path.");
            }
        }
    }

    private static String[][] fillArray(Scanner scanner, String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        String[][] matrixWithAllWords = new String[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            matrixWithAllWords[i] = lines.get(i).split("\\s+");
        }
        return matrixWithAllWords;
    }
}


