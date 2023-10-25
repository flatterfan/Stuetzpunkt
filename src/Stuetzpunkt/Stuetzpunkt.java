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
        Funker 1 [][][][][][][][][][][][][][][][][][][][][][]][][]
        Funker 2 [][][][][][][][][][][][][][][][][][][][][][]][][]
        .
        .
        .
         */
        this.days = days;
    }

    public void setTable() {
        table[0] = new byte[]{0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0};
        table[1] = new byte[]{0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[2] = new byte[]{0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[3] = new byte[]{0, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[4] = new byte[]{4, 4, 4, 4, 4, 4, 1, 1, 1, 4, 4, 4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4};
        table[5] = new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0};
        table[6] = new byte[]{0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[7] = new byte[]{0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[8] = new byte[]{0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[9] = new byte[]{0, 5, 1, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[10] = new byte[]{0, 5, 0, 2, 5, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        table[11] = new byte[]{0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.deepToString(table));
        System.out.println(checkHunters());
    }

    public boolean isValidTable() {


        return true;
    }

    private boolean checkFaehnriche() {
        for (int day = 0; day < days; day++) {
            for (int i = 0; i < 2; i++) {
                int sum = 0;
                for (int j = day * 24; j < (day + 1) * 24; j++) {
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
        }
        return true;
    }

    private boolean checkStabsgefreite() {
        for (int day = 0; day < days; day++) {
            for (int i = 2; i < 4; i++) {
                int sum = 0;
                for (int j = day * 24; j < (day + 1) * 24; j++) {
                    byte curr = table[i][j];
                    if (curr == 5) {
                        sum++;
                    } else if (curr == 3 || curr == 4) {
                        return false;
                    }
                }
                if (sum != 3) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkFunker() {
        byte[] funker1 = table[4];
        byte[] funker2 = table[5];

        for (int day = 0; day < days; day++) {
            int both = 0;
            for (int i = day * 24; i < (day + 1) * 24; i++) {
                byte curr1 = funker1[i];
                byte curr2 = funker2[i];
                if (curr1 == 4 && curr2 == 4) {
                    both++;
                } else if (curr1 != 4 && curr2 != 4) {
                    return false;
                } else if (curr1 == 2 || curr1 == 3 || curr1 == 5 || curr2 == 2 || curr2 == 3 || curr2 == 5) {
                    return false;
                }
            }
            if (both != 6) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHunters() {
        for (int day = 0; day < days; day++) {
            for (int i = 6; i < 12; i++) {
                int sum = 0;
                for (int j = day * 24; j < (day + 1) * 24; j++) {
                    if (table[i][j] == 5) {
                        sum++;
                    } else if (table[i][j] == 4) {
                        return false;
                    }
                }
                if (sum != 2) {
                    return false;
                }
            }
        }


        return true;
    }

}
