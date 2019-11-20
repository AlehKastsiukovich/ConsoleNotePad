package by.etc.part6.notepad;


public class NoteBookView {

    public void showAllNotes(NoteBook noteBook) {
        for (Note note: noteBook.getNoteList()) {
            System.out.println(note.toString());
        }
    }

    public void printNote(Note note) {
        System.out.println(note.getTopic() + "/" + note.getEmail() + "/" + note.getLocalDate());
    }
}
