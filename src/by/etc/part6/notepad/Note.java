package by.etc.part6.notepad;


import java.io.Serializable;
import java.time.LocalDate;


public class Note implements Comparable<Note>, Serializable {
    private String topic;
    private String email;
    private String message;
    private LocalDate localDate;

    public Note() {
        this.localDate = java.time.LocalDate.now();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public int compareTo(Note o) {
        int result = this.topic.compareTo(o.topic);
        return result;
    }

    public String toString() {
        return topic + "/" + email + "/" + localDate;
    }
}
