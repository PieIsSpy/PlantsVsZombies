import java.util.ArrayList;

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
        System.out.print("\t");
        displayChar(1, '+');
        for (i = 0; i < COLUMNS; i++) {
            displayChar(4, ' ');
            displayChar(1, '+');
        }
    }

    public void displayContent(Plant[] p, ArrayList<Zombie> z, int r) {
        int i;
        int j;
        boolean found;
        String output = String.valueOf(r);
        output = output.concat("\t");
        //System.out.print(r + "\t");

        // display plants and zombies
        for (i = 0; i < COLUMNS; i++) {
            if (p[i] != null) {
                if (p[i].getName().equalsIgnoreCase("sunflower"))
                    output = output.concat("   S");
                else if (p[i].getName().equalsIgnoreCase("peashooter"))
                    output = output.concat("   P");
            }
            else {
                j = 0;
                found = false;

                // find a zombie in that col
                while (j < z.size() && !found) {
                    if (i == (int)z.get(j).getCol() && z.get(j).getRow() == r-1) {
                        output = output.concat("    Z");
                        found = true;
                    }
                    j++;
                }

                if (!found)
                    output = output.concat("     ");
            }
        }

        System.out.print(output);
        System.out.println();
    }

    public void displayLawn(Plant[][] p, ArrayList<Zombie> z) {
        int i;
        // print number of cols
        System.out.print("\t");
        for (i = 1; i <= COLUMNS; i++) {
            if (i <= 10)
                System.out.printf("  %d  ", i);
            else
                System.out.printf(" %d  ", i);
        }
        System.out.println();
        displayLine();

        // print out spaces in each grid
        System.out.println();
        for (i = 0; i < ROWS; i++) {
            displayContent(p[i], z, i + 1);
            displayLine();
            System.out.println();
        }
    }

    private final int ROWS;
    private final int COLUMNS;
}

class LawnDriver {
    public static void main(String[] args) {
        Lawn display = new Lawn(6, 19);
        Plant[][] p = new Plant[6][19];
        ArrayList<Zombie> z = new ArrayList<Zombie>();

        //p[0][0] = new Sunflower(0,0);
        p[0][4] = new Peashooter(0, 4);
        p[1][1] = new Peashooter(1,1);
        z.add(new Zombie(0,3));
        //z.add(new Zombie(1,3));
        z.add(new Zombie(3, 0));
        z.add(new Zombie(0, 0));

        display.displayLawn(p, z);
    }
}