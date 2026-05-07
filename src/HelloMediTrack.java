import java.time.LocalDate;

public class HelloMediTrack {

    public static void main(String[] args) {
        // Sprint 0 verification - confirms environment is working
        System.out.println("MediTrack Patient System - v0.1");
        System.out.println("Developer: Khai Zhe Koay"); // replace with your name
        System.out.println("Student ID: YOUR STUDENT ID"); // replace

        // Get current date
        LocalDate today = LocalDate.now();

        // Welcome message
        System.out.println("Welcome to MediTrack, Khai Zhe Koay. Today is " + today + ".");

        System.out.println("Status: Environment configured successfully.");
    }
}