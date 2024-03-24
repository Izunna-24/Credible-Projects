package dearDiary.dtos;

public class DeleteEntryRequest {
    private int id;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
