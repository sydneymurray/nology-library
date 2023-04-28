package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberServices {
    private Scanner keyboardInput = new Scanner(System.in);
    private SaveMemberDB saveMemberDB = new SaveMemberDB();
    public void registerANewMember(LibraryInformation libraryInformation) {
        String name = null;
        String password = null;
        String email = null;

        System.out.println("\n     Please enter your name: ");
        try {
            name = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("\nIncorrect entry");
            return;
        }

        System.out.println("\n     Please enter a new password: ");
        try {
            password = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("\nIncorrect entry");
            return;
        }

        System.out.println("\n     Please enter your email address");
        try {
            email = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("\nIncorrect entry");
            return;
        }

        if (name.equals("") || email.equals("") || password.equals("")) return;
        System.out.println("Congratulations. Your application has been accepted");
        libraryInformation.AddNewMember(new Member(name, email, password));
    }

    public Member LoginMember(ArrayList<Member> members) {
        Scanner keyboardInput = new Scanner(System.in);
        String email;
        String password;

        System.out.println("\n     Please enter your email address: ");
        try {
            email = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("\nIncorrect entry");
            return null;
        }

        System.out.println("\n     Please enter your password: ");
        try {
            password = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("\nIncorrect entry");
            return null;
        }

        if (email.equals("") || password.equals("")) return null;
        for (Member member : members) {
            if (email.equals(member.getEmail()) && password.equals(member.getPassword())) {
                System.out.println("\nLogin Successful");
                return member;
            }
        }
        System.out.println("\nLogin Unsuccessful");
        return null;
    }

    public void upgradeMembership(Member loggedInMember, ArrayList<Member> members) {
        File adminPasswordfile = new File("./src/main/resources/admin_password.txt");
        String userInput;
        String password;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(adminPasswordfile));
            password = fileReader.readLine();

        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("admin_password file not found");
            return;
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("Could not load CSV file due to an IO error");
            return;
        }

        System.out.println("    Please enter upgrade password");
        try {
            userInput = keyboardInput.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            return;
        }
        if (!userInput.equals(password)){
            System.out.println("    Incorrect password");
            returnToMainMenu();
            return;
        }

        loggedInMember.setAdministrator(true);
        saveMemberDB.saveMemberDataToJSONFile(members);
        System.out.println("    You have successfully been upgraded to an administrator");
        returnToMainMenu();
    }

    private void displayMembers(ArrayList<Member> members) {
        for (Member member : members) {
            System.out.printf("%6d %20s %20s %20s %8s %3d %3d %n",
                    member.getId(), member.getName(), member.getPassword(), member.getEmail(), member.getAdministrator(),
                    member.getEmail().length(), member.getPassword().length());
        }
    }
    private void returnToMainMenu(){
        System.out.println("\n     Press enter to return to the main menu");
        try {
            String userInput = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
}
