import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        boolean[][] schedule = new boolean[8][60];
        AppointmentBook a = new AppointmentBook(schedule);
        for(int i = 10; i < 15; i++) schedule[1][i] = true;
        for(int i = 30; i < 45; i++) schedule[1][i] = true;
        for(int i = 50; i < 60; i++) schedule[1][i] = true;
        a.printPeriod(2);
        System.out.println("-----------------\n\n\n\n-----------------");
        System.out.println(a.findFreeBlock(2,15));
        System.out.println("-----------------\n\n\n\n-----------------");
        schedule = new boolean[8][60];
        for(int i = 25; i < 30; i++) schedule[1][i] = true;
        for(int i = 0; i < 15; i++) schedule[2][i] = true;
        for(int i = 41; i < 60; i++) schedule[2][i] = true;
        for(int i = 5; i < 30; i++) schedule[3][i] = true;
        for(int i = 44; i < 60; i++) schedule[3][i] = true;
        AppointmentBook b = new AppointmentBook(schedule);
        int period = 2;
        while(period < 5) {
            System.out.println("Period; " + period);
            b.printPeriod(period);
            period++;
        }
        System.out.println("-----------------\n\n\n\n-----------------");
        System.out.println(b.makeAppointment(2,4,22));
        System.out.println(b.makeAppointment(3,4,3));
        System.out.println(b.makeAppointment(2,4,30));
        System.out.println("-----------------\n\n\n\n-----------------");
        b.printPeriod(4);
        System.out.println("-----------------\n\n\n\n-----------------");
        fulfilled();
    }
    public static void fulfilled() throws FileNotFoundException {
        File f = new File("Schedules.txt");
        Scanner scanner = new Scanner(f);
        String[] currentSchedule = new String[60];
        boolean[][] schedule = new boolean[8][60];
        int i = 0;
        int fulfilled = 0;
        int[] intArray = new int[3];
        String[] stringArray = new String[3];
        while (scanner.hasNextLine()) {
            if (i < 8) {
                currentSchedule = scanner.nextLine().split(" ");
                for (int n = 0; n < 60; n++) {
                    schedule[i][n] = Boolean.parseBoolean(currentSchedule[n]);
                }
                i++;
            } else {
                AppointmentBook app = new AppointmentBook(schedule);
                stringArray = scanner.nextLine().split(" ");
                for  (int n = 0; n < 3; n++) {
                    intArray[n] = Integer.parseInt(stringArray[n]);
                }
                if (app.makeAppointment(intArray[0], intArray[1], intArray[2])) fulfilled++;
                i = 0;
            }
        }
        System.out.println("Fulfilled: " + fulfilled);
    }
}