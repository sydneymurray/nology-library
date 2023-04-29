package org.example;

import java.io.File;
import java.util.ArrayList;

public class LibraryInformation {
    private ArrayList<Book> libraryContent = new ArrayList<Book>();
    private ArrayList<Member> libraryMembers = new ArrayList<Member>();
    public LoadCSVBookLibrary loadCSVBookLibrary = new LoadCSVBookLibrary();
    public LoadJSONBookLibrary loadJSONBookLibrary = new LoadJSONBookLibrary();
    public LoadMemberDB loadMemberDB = new LoadMemberDB();
    public SaveMemberDB saveMemberDB = new SaveMemberDB();
    private File csvBookDataFile = new File("./src/main/resources/books_data.csv");
    private File jsonBookDataFile = new File("./src/main/resources/books_data.json");
    private File memberDataFile = new File("./src/main/resources/member_data.json");

    public ArrayList<Book> getLibraryContent() {
        return libraryContent;
    }


    public ArrayList<Member> getLibraryMembers() {
        return libraryMembers;
    }

    public int retrieveLibraryData() {
        if (!jsonBookDataFile.exists()) libraryContent = loadCSVBookLibrary.readCSVFile(csvBookDataFile);
        else libraryContent = loadJSONBookLibrary.readJSONFile();
        if (memberDataFile.exists()) libraryMembers = loadMemberDB.loadJSONMembershipFile();
        return libraryContent.size();
    }

    public void AddNewMember(Member newMember) {
        libraryMembers.add(newMember);
        saveMemberDB.saveMemberDataToJSONFile(libraryMembers);
    }
}
