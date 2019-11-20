package by.etc.part6.notepad;


public class Main {

    public static void main(String[] args) {
        NoteBook noteBook = new NoteBook();
        NoteBookLogic logic = new NoteBookLogic();
        NoteBookView view = new NoteBookView();

        logic.startProgram(noteBook);

        view.showAllNotes(noteBook);

        logic.addNoteToList(noteBook, logic.createNote());
        logic.addNoteToList(noteBook, logic.createNote());

        view.showAllNotes(noteBook);

        FileListWriter.writeList(noteBook.getNoteList());
    }
}
