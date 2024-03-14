import java.util.Scanner;

public class BattleShip {
    public static void main(String[] args) {
        Board gBoard = new Board();
        boolean s = true;
        Scanner scan = new Scanner(System.in);

        while (s) {
            
            System.out.println("'a' to add a new ship, 'b' to see the board, 'p' to play, or 'q' to quit.");
            String s2 = scan.nextLine();

            if (s2.equals("a")) {
                System.out.println("Enter Row (A-J): ");
                String s3 = scan.nextLine();
                char row = s3.charAt(0);
                int column = InputHelper.getPositiveNonZeroInt(scan, "Enter Column 1-10: ");
                System.out.println("Enter Ship Length: ");

                int length;
                while (true) {
                    try {
                        length = Integer.parseInt(scan.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer for ship length.");
                    }
                }

                System.out.println("Enter Ship Orientation (true for horizontal, false for vertical): ");
                boolean horizontal = Boolean.parseBoolean(scan.nextLine());

                if (gBoard.placeShip(horizontal, length, row, column)) {
                    System.out.println("New Ship Added!");
                } else {
                    System.out.println("Can't put a ship there.");
                }

            } else if (s2.equals("b")) {
                gBoard.printBoard();

            } else if (s2.equals("p")) {
                if (gBoard.searchShips(3) && gBoard.searchShips(4)) {
                    System.out.println("Ok let's play!");
                    boolean sentinel = true;
                    while (sentinel) {
                        if (gBoard.winCheck()) {
                            System.out.println("You won!");
                            System.exit(0);
                        }
                        System.out.println("Press 's' to shoot at a square, 'b' to see the board, 'q' to quit.");
                        String temp2 = scan.nextLine();
                        if (temp2.equals("s")) {
                            System.out.println("Enter Row (A-J): ");
                            String s3 = scan.nextLine();
                            char row = s3.charAt(0);
                            int column = InputHelper.getPositiveNonZeroInt(scan, "Enter int 1-10: ");
                            int result = gBoard.shoot(row, column);
                            if (result == 0) {
                                System.out.println("miss");
                            } else if (result == 1) {
                                System.out.println("hit");
                            } else if (result == 2) {
                                System.out.println("already guessed here");
                            } else if (result == -1) {
                                System.out.println("invalid location");
                            }
                        } else if (temp2.equals("q")) {
                            System.exit(0);
                        } else if (temp2.equals("b")) {
                            gBoard.printBoard();
                        }
                    }

                } else {
                    System.out.println("Ships with len 3 & 4 needed.");
                }

            } else if (s2.equals("q")) {
                break;
            }
        }

        
    }
}
