package by.etc.part6.notepad;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class NoteBook implements Runnable {
    private static File file = new File("notes.bin");
    private Note note;
    private List<Note> noteList;
    private Validator validator;

    public NoteBook() {
        noteList = new ArrayList<>();
        validator = new Validator();
    }

    public void createFile() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> list) {
        if (list != null) {
            this.noteList = list;
        } else {
            System.out.println("File empty!");
        }
    }

    public void addNoteToList(Note note) {
        noteList.add(note);
    }

    public void sortListByTitle(List<Note> notes) {
        Collections.sort(notes);

        for (Note n: notes) {
            System.out.println(n.toString());
        }
    }

    public Note createNote() {
        Scanner scanner = new Scanner(System.in);
        note = new Note();

        System.out.println("3 Steps to create new note!");

        System.out.println("Enter topic name: ");
        note.setTopic(scanner.nextLine());

        System.out.println("Enter email: ");
        String mail = null;
        while (true) {
            mail = scanner.nextLine();
            if (validator.validateMail(mail)) {
                note.setEmail(mail);
                break;
            } else {
                System.out.println("email incorrect try again!");
            }
        }

        System.out.println("Enter message: ");
        note.setMessage(scanner.nextLine());

        return note;
    }

    public void showAllNotes() {
        for (Note note: noteList) {
            System.out.println(note.toString());
        }
    }

    public void findByTopic(List<Note> list) {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        for(Note n: list) {
            if (n.getTopic().matches(".*" + title + ".*")) {
                System.out.println(n.toString());
            }
        }
    }

    public void findByEmail(List<Note> list) {
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();

        for(Note n: list) {
            if (n.getEmail().matches(".*" + email + ".*")) {
                System.out.println(n.toString());
            }
        }
    }

    public void findByDate(List<Note> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date of note which you want to find. Format year-month-day: ");
        String date = scanner.nextLine();

        if (validator.validateDate(date)) {
            for(Note n: list) {
                if (n.getLocalDate().toString().matches(".*" + date + ".*")) {
                    System.out.println(n.toString());
                }
            }
        } else {
            System.out.println("Such date does not exist or you entered wrong format!");
        }
    }

    public void findByWord(List<Note> list) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        for(Note n: list) {
            if (n.getMessage().matches(".*" + word + ".*")) {
                System.out.println(n.toString());
            }
        }
    }

    @Override
    public void run() {
        if(!file.exists()) {
            createFile();
            System.out.println("File has been created!");
        }

        try(ObjectInputStream oi = new ObjectInputStream(new FileInputStream("notes.bin"))) {
            this.noteList = (List<Note>) oi.readObject();
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startProgram(NoteBook noteBook) {
        Thread thread = new Thread(noteBook);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
