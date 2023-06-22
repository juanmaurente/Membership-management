package org.example;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileHandler {

    public LinkedList<Member> readFile(){
        LinkedList<Member> listOfMembers = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Member member;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))){
            lineRead = reader.readLine();

            while (lineRead != null){
                splitLine = lineRead.split(", ");
                if (splitLine[0].equals("S"))
                {
                    member = new SingleClubMember('S', Integer.parseInt(splitLine[1]),
                            splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                }else
                {
                    member = new MultiClubMember('M', Integer.parseInt(splitLine[1]),
                            splitLine[2], Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4]));
                }

                listOfMembers.add(member);
                lineRead = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return listOfMembers;
    }

    public void appendFile(String member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv",
                true))) {
            writer.write(member + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void overWriteFile(LinkedList<Member> listOfMembers){
        // We cannot delete data from a file. We need a temp file that holds the original data,
        // remove the member and then overWrite the file

        String s;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))){
            for (int i=0; i< listOfMembers.size(); i++) {
                s = listOfMembers.get(i).toString();
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            File f = new File("members.csv");
            File tf = new File("members.temp");

            //delete old file and rename temp to file
            f.delete();
            tf.renameTo(f);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
