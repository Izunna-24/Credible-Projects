package dearDiary.data.repositories;

import dearDiary.data.models.Entry;

import java.util.List;

public interface EntryRepository {
    Entry save(Entry entry);
    List<Entry> findAll();
    Entry findById(int id);
    Entry findByAuthor();
    long count();
    void delete(int id);
    void delete(Entry entry);


    List<Entry> findTheAuthorStuff(String author);

}
