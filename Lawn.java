public class Lawn {
    public Lawn(int r, int c) {
        ROWS = r;
        COLUMNS = c;
    }

    public void displayChar(int n, char c) {
        int i;
        for (i = 0; i < n; i++)
            System.out.print(c);
    }

    public void displayLine() {
        int i, j;
        displayChar(1, '+');
        for (i = 0; i < COLUMNS; i++) {
            displayChar(4, '-');
            displayChar(1, '+');
        }
    }

    public void displayLawn() {

    }

    private final int ROWS;
    private final int COLUMNS;
}
