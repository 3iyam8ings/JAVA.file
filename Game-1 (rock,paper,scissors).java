
import java.util.Random;
import java.util.Scanner;

public class game1 {

   private static final String[] CHOICES ={"Rock", "Paper", "Scissors"} ;
    private static int userScore = 0 ;
    private static int computerScore = 0 ;
    private static int ties = 0 ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("==== Welcome to Rock-Paper-Scissors Game ===");
        System.out.println(" Rules: Rock beats Scissors, Scissors beats Paper, Paper beats Rock ") ;

        boolean keepPlaying = true ;
        
        while (keepPlaying) {
            System.out.println("\n---Main Menu---");
            System.out.println("1. Play a Round");
            System.out.println("2. Play Best of N Rounds");
            System.out.println("3. View Scoreboard");
            System.out.println("4. Exit Game");
            System.out.print("Enter your choice: ");

            int Choice = getValidInteger(scanner);
            switch (Choice) {
                case 1:
                    playSingleRound(scanner, random);
                    break;
                case 2:
                    playBestOfNRounds(scanner, random);
                    break;
                case 3:
                    displayScoreboard() ;
                    break;
                case 4:
                    keepPlaying = false;
                    System.out.println("Thanks for playing! Goodbye 👋");
                    break;
                default:
                    System.out.println("⚠ Invalid choice. Please select 1-4.");
            } }
        scanner.close();
    }

    // --- Method to play a single round ---
    private static void playSingleRound(Scanner scanner, Random random) {
        System.out.println("\nChoose: 0 = Rock, 1 = Paper, 2 = Scissors");
        int userChoice = getValidChoice(scanner);

        int computerChoice = random.nextInt(3);

        System.out.println("You chose: " + CHOICES[userChoice]);
        System.out.println("Computer chose: " + CHOICES[computerChoice]);

        determineWinner(userChoice, computerChoice);
    }

    // --- Method for Best of N Rounds ---
    private static void playBestOfNRounds(Scanner scanner, Random random) {
        System.out.print("\nEnter how many rounds you want to play (must be odd, e.g., 3, 5, 7): ");
        int rounds = getValidInteger(scanner);

        if (rounds % 2 == 0) {
            System.out.println("⚠ Please enter an ODD number of rounds.");
            return;
        }

        int userWins = 0;
        int compWins = 0;
        int requiredWins = (rounds / 2) + 1;

        System.out.println("\nPlaying Best of " + rounds + " rounds... First to " + requiredWins + " wins!");

        while (userWins < requiredWins && compWins < requiredWins) {
            System.out.println("\nChoose: 0 = Rock, 1 = Paper, 2 = Scissors");
            int userChoice = getValidChoice(scanner);
            int computerChoice = random.nextInt(3);

            System.out.println("You chose: " + CHOICES[userChoice]);
            System.out.println("Computer chose: " + CHOICES[computerChoice]);

            int result = determineWinner(userChoice, computerChoice);

            if (result == 1) userWins++;
            else if (result == -1) compWins++;

            System.out.println("Score => You: " + userWins + " | Computer: " + compWins);
        }

        if (userWins > compWins) {
            System.out.println("🏆 You won the Best of " + rounds + " series!");
        } else {
            System.out.println("💻 Computer won the Best of " + rounds + " series!");
        }
    }

    // --- Determine Winner Logic ---
    private static int determineWinner(int userChoice, int computerChoice) {
        if (userChoice == computerChoice) {
            System.out.println("🤝 It's a tie!");
            ties++;
            return 0;
        } else if ((userChoice == 0 && computerChoice == 2) ||
                   (userChoice == 1 && computerChoice == 0) ||
                   (userChoice == 2 && computerChoice == 1)) {
            System.out.println("✅ You win this round!");
            userScore++;
            return 1;
        } else {
            System.out.println("❌ Computer wins this round!");
            computerScore++;
            return -1;
        }
    }

    // --- Scoreboard Display ---
    private static void displayScoreboard() {
        System.out.println("\n===== Scoreboard =====");
        System.out.println("Your Wins: " + userScore);
        System.out.println("Computer Wins: " + computerScore);
        System.out.println("Ties: " + ties);
        System.out.println("======================");
    }

    // --- Get valid integer input ---
    private static int getValidInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("⚠ Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // --- Get valid choice for Rock/Paper/Scissors ---
    private static int getValidChoice(Scanner scanner) {
        int choice;
        while (true) {
            choice = getValidInteger(scanner);
            if (choice >= 0 && choice <= 2) break;
            System.out.println("⚠ Invalid choice. Enter 0 (Rock), 1 (Paper), or 2 (Scissors).");
        }  return choice; } }
