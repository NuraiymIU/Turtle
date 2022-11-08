import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static String command;
    static int steps;
    static String errorMessage;

    static int x;
    static int y;
    static int direction;
    static boolean isPenDown;

    static char[][] matrix;
    static int width;
    static int height;

    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;

    public static void main(String[] args) {
        run();
    }

    static void run() {
        init();
        readUserCommand();

        while(!command.equals("exit")) {
            switch(command) {
                case "print":
                    print();
                    break;
                case "move":
                    move(steps);
                    break;
                case "turnleft":
                    turnRight();
                    break;
                case "turnright":
                    turnLeft();
                    break;
                case "pendown":
                    penDown();
                    break;
                case "penup":
                    penUp();
                    break;
            }
            readUserCommand();
        }
    }

    static void init() {
        width = 20;
        height = 20;

        x = 0;
        y = 0;
        matrix = new char[width][height];

        for(int row = 0; row < matrix.length; row++) {
            Arrays.fill(matrix[row], '.');
        }
        matrix[y][x] = 'T';
        direction = EAST;

        isPenDown = false;

        errorMessage = "Use one of below:\n" +
                "print - prints matrix 20x20\n" +
                "move 10 - moves 10 steps forward\n" +
                "turnright - turtle turns right\n" +
                "turnleft - turtle turns left\n" +
                "pendown - turtle pen down\n" +
                "penup - turtle pen up\n" +
                "exit - stops the running program";
    }

    static void move(int steps) {
        int oldX = x;
        int oldY = y;
        for(int i = 0; i < steps; i++) {
            if(isPenDown && i < steps - 1) {
                matrix[y][x] = '*';
            } else if(i == steps - 1) {
                matrix[y][x] = 'T';
            }

            switch(direction) {
                case NORTH:
                    y--;
                    break;
                case EAST:
                    x++;
                    break;
                case SOUTH:
                    y++;
                    break;
                case WEST:
                    x--;
                    break;
            }

            if(oldX == 0 && oldY != 0) {
                matrix[oldY - 1][oldX] = isPenDown ? '*' : '.';
            } else if(oldX != 0 && oldY == 0) {
                matrix[oldY][oldX - 1] =  isPenDown ? '*' : '.';
            } else if(oldX == 0 && oldY == 0) {
                matrix[oldY][oldX] =  isPenDown ? '*': '.';
            } else {
                matrix[oldY - 1][oldX - 1] = isPenDown ? '*' : '.';
            }
        }
    }

    static void readUserCommand() {
        String cmd = scan.nextLine().toLowerCase().trim();
        String[] partsOfCmd = cmd.split("\\s+"); // это регулярное выражение дает возможность несколько пробелов воспринимать как один большой пробел

        if(partsOfCmd.length == 1) {
            switch(cmd) {
                case "print":
                case "turnleft":
                case "turnright":
                case "penup":
                case "pendown":
                case "exit":
                    command = partsOfCmd[0];
                    return;

                default :
                    throw new RuntimeException("Incorrect command: " + cmd + "\n" + errorMessage);
            }
        } else if(
                partsOfCmd.length == 2 &&
                        !partsOfCmd[0].equals("move") ||
                        partsOfCmd.length == 0 ||
                        partsOfCmd.length > 2) {
            throw new RuntimeException("Incorrect command: " + cmd + "\n" + errorMessage);
        }

        try {
            command = partsOfCmd[0];
            steps = Integer.parseInt(partsOfCmd[1]);
        } catch(NumberFormatException e) {
            throw new RuntimeException("Steps can not be String: " + partsOfCmd[1]);
        }
    }

    static void turnLeft() {
        if(direction == 0) {
            direction = 3;
        } else {
            direction -= 1;
        }
    }

    static void turnRight() {
        if(direction == 3) {
            direction = 0;
        } else {
            direction += 1;
        }
    }

    static void penDown() {
        isPenDown = true;
    }

    static void penUp() {
        isPenDown = false;
    }

    static void print() {
        for(char[] innerArray : matrix) {
            for(char element : innerArray) {
                System.out.print(element);
            }
            System.out.println();
        }
    }
}
