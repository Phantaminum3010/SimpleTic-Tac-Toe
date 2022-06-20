package tictactoe;

import java.util.Scanner;

public class Main {
    final static int size = 3;
    char[][] ticTacToeGrid;
    int emptyCells;
    int X;
    int Y;
    boolean XWins = false;
    boolean OWins = false;
    boolean draw = false;

    public enum State {
        ENTER_X_COORDINATES, ENTER_O_COORDINATES,
    }

    State state;

    public Main() {
        this.ticTacToeGrid = new char[size][size];
        this.X = 0;
        this.Y = 0;
        this.emptyCells = size * size;
        for (int i = 0; i < this.ticTacToeGrid.length; i++) {
            for (int j = 0; j < this.ticTacToeGrid[i].length; j++) {
                this.ticTacToeGrid[i][j] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        Main ticTacToe = new Main();

        ticTacToe.printGrid();

        ticTacToe.state = State.ENTER_X_COORDINATES;

        while (!ticTacToe.checkEndGame()) {
            switch(ticTacToe.state) {
                case ENTER_X_COORDINATES:
                    System.out.print("Enter the coordinates: ");
                    ticTacToe.processXCoordinates(scanner.nextLine());
                    break;
                case ENTER_O_COORDINATES:
                    System.out.print("Enter the coordinates: ");
                    ticTacToe.processOCoordinates(scanner.nextLine());
                    break;
                default:
                    break;
            }
        }

        if (ticTacToe.XWins) {
            System.out.println("X wins");
        } else if (ticTacToe.OWins) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < this.ticTacToeGrid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.ticTacToeGrid[i].length; j++) {
                System.out.print(this.ticTacToeGrid[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public boolean checkCharInARow(char symbol) {
        boolean symbolInARow = false;
        for (int i = 0; i < this.ticTacToeGrid.length; i++) {
            if (this.ticTacToeGrid[i][0] == symbol && this.ticTacToeGrid[i][1] == symbol &&
                    this.ticTacToeGrid[i][2] == symbol) {
                symbolInARow = true;
            }
        }

        for (int j = 0; j < this.ticTacToeGrid.length; j++) {
            if (this.ticTacToeGrid[0][j] == symbol && this.ticTacToeGrid[1][j] == symbol &&
                    this.ticTacToeGrid[2][j] == symbol) {
                symbolInARow = true;
            }
        }

        if (this.ticTacToeGrid[0][0] == symbol && this.ticTacToeGrid[1][1] == symbol &&
                this.ticTacToeGrid[2][2] == symbol || ticTacToeGrid[2][0] == symbol && ticTacToeGrid[1][1] == symbol &&
                ticTacToeGrid[0][2] == symbol) {
            symbolInARow = true;
        }
        return symbolInARow;
    }


    public void processXCoordinates(String input) {
        String[] strArray = input.split("\\s");
        if (strArray.length  < 2) {
            System.out.println("You should enter numbers!");
            this.state = State.ENTER_X_COORDINATES;
            return;
        }
        String firstInput = strArray[0];
        String secondInput = strArray[1];
        if (!isInteger(firstInput) && !isInteger(secondInput)) {
            System.out.println("You should enter numbers!");
            this.state = State.ENTER_X_COORDINATES;
            return;
        }
        int xCoordinate = Integer.parseInt(firstInput);
        int yCoordinate = Integer.parseInt(secondInput);

        if (xCoordinate < 1 || xCoordinate > 3 || yCoordinate < 1 || yCoordinate > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            this.state = State.ENTER_X_COORDINATES;
            return;
        }

        if (this.ticTacToeGrid[xCoordinate - 1][yCoordinate - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            this.state = State.ENTER_X_COORDINATES;
        } else {
            this.ticTacToeGrid[xCoordinate - 1][yCoordinate - 1] = 'X';
            this.printGrid();
            this.state = State.ENTER_O_COORDINATES;
        }
        return;
    }

    public void processOCoordinates(String input) {
        String[] strArray = input.split("\\s");
        if (strArray.length  < 2) {
            System.out.println("You should enter numbers!");
            this.state = State.ENTER_O_COORDINATES;
            return;
        }
        String firstInput = strArray[0];
        String secondInput = strArray[1];
        if (!isInteger(firstInput) && !isInteger(secondInput)) {
            System.out.println("You should enter numbers!");
            this.state = State.ENTER_O_COORDINATES;
            return;
        }
        int xCoordinate = Integer.parseInt(firstInput);
        int yCoordinate = Integer.parseInt(secondInput);

        if (xCoordinate < 1 || xCoordinate > 3 || yCoordinate < 1 || yCoordinate > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            this.state = State.ENTER_O_COORDINATES;
            return;
        }

        if (this.ticTacToeGrid[xCoordinate - 1][yCoordinate - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            this.state = State.ENTER_O_COORDINATES;
        } else {
            this.ticTacToeGrid[xCoordinate - 1][yCoordinate - 1] = 'O';
            this.printGrid();
            this.state = State.ENTER_X_COORDINATES;
        }
        return;
    }

    private boolean checkEndGame() {
        if (Math.abs(this.X - this.Y) >= 2 ||
        this.checkCharInARow('X')&& this.checkCharInARow('Y')) {
            //System.out.println("Impossible");
            return false;
        } else if (this.checkCharInARow('X')) {
            //System.out.println("X wins");
            this.XWins = true;
            return true;
        } else if (this.checkCharInARow('O')) {
            //System.out.println("O wins");
            this.OWins = true;
            return true;
        } else if (this.emptyCells > 0) {
            //System.out.println("Game not finished");
            return false;
        } else {
            //System.out.println("Draw");
            this.draw = true;
            return true;
        }
    }
}

