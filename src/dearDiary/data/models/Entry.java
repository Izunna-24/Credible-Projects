package dearDiary.data.models;
import java.time.LocalDateTime;

public class Entry {
    private String author;
    private int id;
    private String title;
    private String body;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private LocalDateTime dateCreated = LocalDateTime.now();

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Entry(String title, String body){
        this.title = title;
        this.body = body;

    }

    public Entry(){
    }

}
