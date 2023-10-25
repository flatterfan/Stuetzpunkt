package Stuetzpunkt;

import java.util.Arrays;

public class Stuetzpunkt {
    byte[][] table;
    // 0 = free time
    // 1 = tent
    // 2 = fire station
    // 3 = hideout
    // 4 = radio
    // 5 = other task
    int days;

    public Stuetzpunkt(int days) {
        this.table = new byte[12][days * 24]; // initialize table with 12 soldiers
        /*
        example for days = 1
        Fähnrich 1 [][][][][][][][][][][][][][][][][][][][][][]][][]
        Fähnrich 2 [][][][][][][][][][][][][][][][][][][][][][]][][]
        Stabsgefreiter 1 [][][][][][][][][][][][][][][][][][][][][][]][][]
        Stabsgefreiter 2 [][][][][][][][][][][][][][][][][][][][][][]][][]
        .
        .
        .
         */
    }

    public void setTable() {
        table[0] = new byte[]{0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0};
        table[1] = new byte[]{0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.deepToString(table));
        System.out.println(checkFaehnriche());
    }

    public boolean isValidTable() {

        return true;
    }

    private boolean checkFaehnriche() {
        for (int i = 0; i < 2; i++) {
            int sum = 0;
            for (int j = 0; j < 24; j++) {
                byte curr = table[i][j];
                if (curr == 5) {
                    sum++;
                } else if (curr == 2 || curr == 3 || curr == 4) {
                    return false;
                }
            }
            if (sum != 4) {
                return false;
            }
        }
        return true;
    }


}
