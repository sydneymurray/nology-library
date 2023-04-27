package org.example;

import java.io.*;
import java.util.ArrayList;

public class SaveMemberDB {
    File memberDataFile = new File("./src/main/resources/member_data.json");

    public void saveMemberDataToJSONFile(ArrayList<Member> members) {
        Boolean jsonSeparator = false;
        if (memberDataFile.exists()) memberDataFile.delete();
        try {
            memberDataFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create " + memberDataFile);
        }

        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(memberDataFile));
            fileWriter.write("[");
            for (Member member: members) {
                fileWriter.write(jsonSeparator ? "," : "");
                fileWriter.write("\n  {   \n    \"id\":" + String.valueOf(member.getId()) + "," +
                        "\n    \"name\":" + "\"" + member.getName() + "\"," +
                        "\n    \"email\":" + "\"" + member.getEmail() + "\"," +
                        "\n    \"password\":" + "\"" + member.getPassword() + "\"," +
                        "\n    \"administrator\":" + member.getAdministrator() +
                        "\n  }"
                );
                jsonSeparator = true;
            }
            fileWriter.write("\n]\n");
            fileWriter.close();
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("Could not save members db file due to an IO error");
        }
    }
}
