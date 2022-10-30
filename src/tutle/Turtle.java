package tutle;

public class Turtle {
    private int x;
    private int y;
    private int direction;
    private boolean isPenDown;

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private Canvas canvas;

    public Turtle(int x, int y, int direction, Canvas canvas) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.canvas = canvas;

        canvas.setData(y, x, 'T');
    }

    void move(int steps) {
        int oldX = x;
        int oldY = y;
        for(int i = 0; i < steps; i++) {
            if(isPenDown && i < steps - 1) {
                canvas.setData(y, x, '*');
            } else if(i == steps - 1) {
                canvas.setData(y, x, 'T');
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
        }

        if(oldX == 0 && oldY != 0) {
            canvas.setData(oldY - 1, oldX, isPenDown ? '8' : '=');
        } else if(oldX != 0 && oldY == 0) {
            canvas.setData(oldY, oldX - 1, isPenDown ? '5' : 'p');
        } else if(oldX == 0 && oldY == 0) {
            canvas.setData(oldY, oldX, isPenDown ? '*': '.');
        } else {
            canvas.setData(oldY - 1, oldX, isPenDown ? '*' : '.');
        }
    }
}
