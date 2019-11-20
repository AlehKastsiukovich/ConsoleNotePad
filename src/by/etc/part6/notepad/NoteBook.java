package by.etc.part6.notepad;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteBook {
    private static File file = new File("notes.bin");
    private List<Note> noteList;
    private Validator validator;

    public NoteBook() {
        noteList = new ArrayList<>();
        validator = new Validator();
    }

    public File getFile() {
        return file;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public Validator getValidator() {
        return validator;
    }

    public static void setFile(File file) {
        NoteBook.file = file;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setNoteList(List<Note> list) {
        if (list != null) {
            this.noteList = list;
        } else {
            System.out.println("File empty!");
        }
    }
}
