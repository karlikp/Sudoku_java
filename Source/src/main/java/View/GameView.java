package View;

import java.util.Scanner;

public class GameView {
    private Scanner scanner = new Scanner(System.in);

    // Pobieranie imienia od użytkownika
    public String downloadName() {
        System.out.print("Type in your name: ");
        return scanner.nextLine();
    }

    // Wyświetlanie opcji poziomu trudności
    public int downloadDifficultyLevel() {
        System.out.println("Choose difficulty level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Type in difficulty lavel number (1, 2 lub 3): ");
        return scanner.nextInt();
    }

    // Wyświetlanie powitania gracza
    public void showGreeting(String name, String difficultyLEvel) {
        System.out.println("\nHello, " + name + "! You chose difficulty level: " + difficultyLEvel + ".");
    }
}
