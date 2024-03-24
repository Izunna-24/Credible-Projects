package dearDiary.data.repositories;

import dearDiary.data.models.Entry;

import java.util.List;

public interface EntryRepository {
    Entry save(Entry entry);
    List<Entry> findAll();
    Entry findById(int id);
    List<Entry> findByAuthor(String author);
    long count();
    void delete(int id);
    void delete(Entry entry);




}
