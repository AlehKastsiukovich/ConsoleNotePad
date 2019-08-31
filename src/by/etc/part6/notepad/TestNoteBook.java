package by.etc.part6.notepad;


public class TestNoteBook {

    public static void main(String[] args) {
        NoteBook noteBook = new NoteBook();
        noteBook.startProgram(noteBook);

        noteBook.showAllNotes();

        noteBook.addNoteToList(noteBook.createNote());
        noteBook.addNoteToList(noteBook.createNote());

        FileListWriter.writeList(noteBook.getNoteList());
    }
}
