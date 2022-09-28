import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var random = new Random();
        System.out.println("Hello world!");

        int[] ranArr = new int[10];
        for (int i = 0; i < ranArr.length; i++) {
            ranArr[i] = random.nextInt(1000000);
        }
        int sum = 0;
        for (int i = 0; i < ranArr.length; i++) {
            System.out.println(ranArr[i]);
            sum += ranArr[i];
        }
        System.out.println("Sum = " + sum);

        Scanner inptObj = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String userName = inptObj.nextLine();
        int userAge = -1;
        do {
            System.out.println("Enter your age: ");
            userAge = inptObj.nextInt();
        }
        while (userAge < 0);

        if (userAge >= 18) {
            System.out.println("You are eligible to vote");
            if (userAge >= 30) {
                System.out.println(" and appear for senate.\n");
            }
        }

        if (userAge > 80) {
            System.out.println("You are a part of greatest generation!");
        }
        else if (userAge > 60) {
            System.out.println("You are a part of baby boomers!");
        }
        else if (userAge > 40) {
            System.out.println("You are a part of generation X!");
        }
        else if (userAge > 20) {
            System.out.println("You are a part of millennials!");
        }
        else {
            System.out.println("You are an iKid!");
        }
    }
}