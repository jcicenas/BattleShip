import java.util.*;

public class Board {
    private HashMap<String, Character> board;

    public Board() {
        this.board = new HashMap<>();
        populateMap();
    }

    private void populateMap() {
        for (char c = 'a'; c <= 'j'; c++) {
            for (int i = 1; i <= 10; i++) {
                String temp = String.valueOf(c) + i;
                this.board.put(temp, '-');
            }
        }
    }

    public void printBoard() {
        for (int i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (char c = 'a'; c <= 'j'; c++) {
            System.out.print(c + " ");
            for (int i = 1; i <= 10; i++) {
                String temp = String.valueOf(c) + i;
                System.out.print(this.board.get(temp) + " ");
            }
            System.out.println();
        }
    }

    public boolean placeShip(boolean horizontal, int length, char row, int column) {
        HashMap<String, Character> savedHashMap = new HashMap<>(this.board);

        if (horizontal) {
            for (int i = column; i <= column + length - 1; i++) {
                String temp = String.valueOf(row) + i;
                if (board.containsKey(temp)) {
                    this.board.replace(temp, 'b');
                } else {
                    this.board.putAll(savedHashMap);
                    return false;
                }
            }
        } else {
            for (char c = row; c <= (char) (row + length - 1); c++) {
                String temp = String.valueOf(c) + column;
                if (board.containsKey(temp)) {
                    this.board.replace(temp, 'b');
                } else {
                    this.board.putAll(savedHashMap);
                    return false;
                }
            }
        }

        return true;
    }

    public boolean winCheck() {
        return !board.containsValue('b');
    }

    public boolean searchShips(int length) {
        for (char row = 'a'; row <= 'j'; row++) {
            for (int column = 1; column <= 10; column++) {
                if (checkHorizontalShip(row, column, length) || checkVerticalShip(row, column, length)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkHorizontalShip(char row, int column, int length) {
        for (int i = column; i <= column + length - 1; i++) {
            String temp = String.valueOf(row) + i;
            if (!board.containsKey(temp) || board.get(temp) != 'b') {
                return false;
            }
        }
        return true;
    }

    private boolean checkVerticalShip(char row, int column, int length) {
        for (char c = row; c <= (char) (row + length - 1); c++) {
            String temp = String.valueOf(c) + column;
            if (!board.containsKey(temp) || board.get(temp) != 'b') {
                return false;
            }
        }
        return true;
    }

    public int shoot(char row, int column) {
        String temp = String.valueOf(row) + column;
        if (board.containsKey(temp)) {
            if (board.get(temp) == 'b') {
                board.replace(temp, 'x');
                return 1;
            } else if (board.get(temp) == 'm' || board.get(temp) == 'x') {
                return 2;
            }
        }

        return -1;
    }
}
