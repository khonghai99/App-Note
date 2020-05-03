package com.example.appnote;

public class Note {
    private int ID;
    private String nameNote;
    private String dateNote;

    public int getID() {
        return ID;
    }

    public String getNameNote() {
        return nameNote;
    }

    public String getDateNote() {
        return dateNote;
    }

    public Note(int ID, String nameNote, String dateNote) {
        this.ID = ID;
        this.nameNote = nameNote;
        this.dateNote = dateNote;
    }
}
