package org.school.timetableschedulingsystem.scheduler;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Assign {
    private static final int DAYS = 5;
    private static final int TIME_SLOTS = 11;
    private static final int BREAK_1 = 3;
    private static final int BREAK_2 = 6;
    static String[] subjects = {"Mat", "Eng", "Sci", "SST"};


    static Random rd = new Random();

    public static String[][] createTimetable() {
        String[][] table = new String[DAYS][TIME_SLOTS];


        //4 lessons cant go consecutive
        //[x][1]-[][2]-[][3]-[][4] not allowed

        while (Arrays.stream(table).flatMap(Arrays::stream).anyMatch(Objects::isNull)) {
            int[] position = {
                    rd.nextInt(DAYS),
                    rd.nextInt(TIME_SLOTS),
                    rd.nextInt(subjects.length)};

            if (
                    table[position[0]][position[1]] != null && isAfterOrBeforeBreak(table, position)
            ) {
                continue;
            }

            if (subjectCantBeAfterBreak("Mat", 2, position)) {
                continue;
            }


            //making sure we don't have 4 consecutive

            if (position[1] != 0 && position[1] != TIME_SLOTS - 1 && table[position[0]][position[1] + 1] != null && table[position[0]][position[1] - 1] != null)
                if (subjects[position[2]].equals(table[position[0]][position[1] + 1]) || subjects[position[2]].equals(table[position[0]][position[1] - 1])) {
                    continue;
                }


            table[position[0]][position[1]] = subjects[position[2]];

            //setting double
            setDouble(table, position);

        }
        return table;

    }


    public static boolean isAfterOrBeforeBreak(String[][] table, int[] position) {
        int x = position[0];
        int y = position[1];
        int z = position[2];
        if (y == BREAK_1 || y == BREAK_2) {
            if (table[x][y + 1] != null && subjects[z].equals(table[x][y + 1])) {
                return true;
            }
        }
        if (y == BREAK_1 + 1 || y == BREAK_2 + 1) {
            if ((table[x][y - 1] != null && subjects[z].equals(table[x][y - 1]))) {
                return true;
            }
        }
        return false;
    }

    public static void setDouble(String[][] table, int[] position) {
        int x = position[0];
        int y = position[1];
        int z = position[2];

        if (y == 0 || y == 2 || y == BREAK_1+1 || y == BREAK_2+1) {
            table[x][y + 1] = subjects[z];
        }

        if (y == 1 || y == BREAK_1 || y == BREAK_2 || y == TIME_SLOTS) {
            table[x][y - 1] = subjects[z];
        }

    }

    public static void printTimetable(String[][] timetable) {
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < TIME_SLOTS; j++) {
                System.out.print(timetable[i][j] + "  ");
                if (j == BREAK_1 || j == BREAK_2) {
                    System.out.print("|\t|");
                }
            }
            System.out.println("\n");
        }

    }


    public static void main(String[] args) {
        var timetable = createTimetable();
        printTimetable(timetable);
    }

    public static boolean subjectCantBeAfterBreak(String subject, int abreak, int[] position) {
        if (abreak == 2) {
            abreak = BREAK_2;
        } else abreak = BREAK_1;
        return subjects[position[2]].equals(subject) && position[1] > abreak;
    }
}



