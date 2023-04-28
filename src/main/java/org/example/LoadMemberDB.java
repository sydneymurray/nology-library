package org.example;

import java.io.*;
import java.util.ArrayList;

public class LoadMemberDB {
    private ArrayList<Member> members = new ArrayList<Member>();
    private File memberDataFile = new File("./src/main/resources/member_data.json");

    public ArrayList<Member> loadJSONMembershipFile() {
        String JSONString = buildJSONString();
        JSONString = JSONString.replace("[", "");
        JSONString = JSONString.replace("]", "");
        String[] JSONStringArr = JSONString.split("},  ");
        for (String JSON : JSONStringArr) {
            members.add(createAMember(JSON));
        }
        return members;
    }

    private Member createAMember(String JSON) {
        JSON = JSON.replace("{    ", "");
        JSON = JSON.replace("}", "");
        String[] memberKeys = JSON.split(",");
        Member member = new Member(
                getKeyValue(memberKeys[1]),
                getKeyValue(memberKeys[2]),
                getKeyValue(memberKeys[3])
        );
        member.setId(Integer.parseInt(getKeyValue(memberKeys[0])));
        member.setAdministrator(Boolean.parseBoolean(getKeyValue(memberKeys[4])));
        return member;
    }

    private String getKeyValue(String key) {
        key = key.replace("\"", "");
        String[] value = key.split(":");
        return value[1];
    }

    private String buildJSONString() {
        String JSONString = "";
        String dataLine;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(memberDataFile));
            while ((dataLine = fileReader.readLine()) != null) {
                JSONString += dataLine;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Members JSON file not found");
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("Could not load Members JSON file due to an IO error");
        }
        return JSONString;
    }
}
