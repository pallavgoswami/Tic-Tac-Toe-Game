package com.company;

import java.util.*;//multiple imports

public class Main {

    //creating an array list for winning patterns of player positions
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();//array list of defined winning player positions
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();//array list of defined winning cpu positions

    public static void main(String[] args) {
        // printing the game board as a 2-D Array and symbols as characters
        char[][] gameBoard = {{' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}, {'-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-'}, {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}, {'-', '-', '-', '+', '-', '-', '-', '+', '-', '-', '-'}, {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}};

        printGameBoard(gameBoard);//printing game board through function/method defined below

        //throwing a while true loop so that the game board keeps repeating
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPosition = scan.nextInt();//stores player position

            //creating a while loop so that user cannot overlap placement
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                System.out.println("Position taken! Enter a correct position");
                playerPosition = scan.nextInt();
            }

            //using placePiece, check winner function/method to check the winner after each player move
            placePiece(gameBoard, playerPosition, "player");
            //check winner after each player move
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            //Generating a random cpuPosition
            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;

            //creating a while loop so that cpu cannot overlap placement
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = rand.nextInt(9) + 1;
            }

            //using placePiece, check winner function/method to check the winner after each cpu move
            placePiece(gameBoard, cpuPosition, "cpu");
            printGameBoard(gameBoard);

            //check winner after each cpu move
            result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
    }

    //method to print Game Board
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char symbol : row) {
                System.out.print(symbol);//print a symbol in each row
            }
            System.out.println();//print a line after each row
        }
    }

    //creating a method for the cpu and the player to enter the placements using switch case
    public static void placePiece(char[][] gameBoard, int position, String user){
        char symbol = ' ';
        if (user.equals("player")){
            symbol = 'X';
            playerPositions.add(position);
        }else if (user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(position);
        }
        switch (position) {
            case 1:
                gameBoard[0][1] = symbol;
                break;
            case 2:
                gameBoard[0][5] = symbol;
                break;
            case 3:
                gameBoard[0][9] = symbol;
                break;
            case 4:
                gameBoard[2][1] = symbol;
                break;
            case 5:
                gameBoard[2][5] = symbol;
                break;
            case 6:
                gameBoard[2][9] = symbol;
                break;
            case 7:
                gameBoard[4][1] = symbol;
                break;
            case 8:
                gameBoard[4][5] = symbol;
                break;
            case 9:
                gameBoard[4][9] = symbol;
                break;
            default:
                break;
        }
    }

    //creating a method to check the winner using list and ArrayList
    //these are all the winning conditions
    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftColumn = Arrays.asList(1,4,7);
        List middleColumn = Arrays.asList(2,5,8);
        List rightColumn = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<List>();//creating a List object to add winning conditions
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(middleColumn);
        winning.add(rightColumn);
        winning.add(cross1);
        winning.add(cross2);

        //for each loop to return the result of list iteration
        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations You Won!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU Wins! Sorry :( ";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "TIE!";
            }
        }
        return "";
    }
}






