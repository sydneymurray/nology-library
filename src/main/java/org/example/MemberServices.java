package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberServices {
    public void registerANewMember(LibraryInformation libraryInformation) {
        Scanner keyboardInput = new Scanner(System.in);
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

    private void displayMembers(ArrayList<Member> members) {
        for (Member member : members) {
            System.out.printf("%6d %20s %20s %20s %8s %3d %3d %n",
                    member.getId(), member.getName(), member.getPassword(), member.getEmail(), member.getAdministrator(),
                    member.getEmail().length(), member.getPassword().length());
        }
    }
}
