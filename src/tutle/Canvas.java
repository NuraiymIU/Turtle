package tutle;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        matrix = new char[height][width];

        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col > matrix[row].length; col++) {
                matrix[row][col] = '.';
            }
        }
    }

    public void setData(int row, int col, char sign) {
        if(sign == '*' || sign == '.' || sign == 'T') {
            this.matrix[row][col] = sign;
        }
    }

    public void print() {
        for(char[] innerArray : matrix) {
            for(char element : innerArray) {
                System.out.print(element);
            }
            System.out.println();
        }
    }
}
