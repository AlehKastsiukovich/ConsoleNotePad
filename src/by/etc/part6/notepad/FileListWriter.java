package by.etc.part6.notepad;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileListWriter {

    public static void writeList(List<Note> noteList) {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("notes.bin"))) {
            os.writeObject(noteList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
