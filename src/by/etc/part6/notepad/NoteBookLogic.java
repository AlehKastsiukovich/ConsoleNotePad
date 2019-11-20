package by.etc.part6.notepad;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NoteBookLogic {

    public void createFile(NoteBook noteBook) {
        try {
            noteBook.getFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNoteToList(NoteBook noteBook, Note note) {
        noteBook.getNoteList().add(note);
    }

    public void sortListByTitle(List<Note> notes) {
        Collections.sort(notes);

        for (Note n: notes) {
            System.out.println(n.toString());
        }
    }


    public Note createNote() {
        Scanner scanner = new Scanner(System.in);
        Note note = new Note();

        System.out.println("3 Steps to create new note!");

        System.out.println("Enter topic name: ");
        note.setTopic(scanner.nextLine());

        System.out.println("Enter email: ");
        String mail = null;
        while (true) {
            mail = scanner.nextLine();

            if (new Validator().validateMail(mail)) {
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
                new NoteBookView().printNote(n);
            }
        }
    }

    public void findByDate(List<Note> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date of note which you want to find. Format year-month-day: ");
        String date = scanner.nextLine();

        if (new Validator().validateDate(date)) {

            for(Note n: list) {

                if (n.getLocalDate().toString().matches(".*" + date + ".*")) {
                    new NoteBookView().printNote(n);
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
                new NoteBookView().printNote(n);
            }
        }
    }

    public void startProgram(NoteBook noteBook) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                if (!noteBook.getFile().exists()) {
                    createFile(noteBook);
                    System.out.println("File has been created!");
                }

                try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("notes.bin"))) {
                    noteBook.setNoteList((List<Note>) oi.readObject());
                } catch (EOFException e) {

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
