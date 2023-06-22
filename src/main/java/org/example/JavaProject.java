package org.example;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args) {

        HashMap<Integer, Integer> myMap = new HashMap<>();

        myMap.put(0,9);
        String mem;
        MembershipManagement mm = new MembershipManagement();
        FileHandler fh = new FileHandler();
        LinkedList<Member> members = fh.readFile();

        int choice = mm.getChoice();

        while (choice != -1)
        {
            switch (choice)
            {
                case 1:
                    mem = mm.addMembers(members);
                    fh.appendFile(mem);
                    break;
                case 2:
                    mm.removeMember(members);
                    fh.overWriteFile(members);
                    break;
                case 3:
                    mm.printMemberInfo(members);
                    break;
                default:
                    System.out.print("\nYou have selected an invalid option.\n\n");
                    break;
            }
            choice = mm.getChoice();
        }
        System.out.println("\nGood Bye");
    }



}