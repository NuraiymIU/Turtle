package tutle;

public class Main {
    public static void main(String[] args) {
        Canvas canvas = new Canvas(10, 10);

        Turtle turtle = new Turtle(0, 0, Turtle.EAST, canvas);

        turtle.move(3);
        canvas.print();
    }
}
