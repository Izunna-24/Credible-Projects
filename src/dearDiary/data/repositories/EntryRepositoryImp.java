package dearDiary.data.repositories;

import dearDiary.data.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImp implements EntryRepository{
    private List<Entry> entries = new ArrayList<>();
    private int countId;
    @Override
    public Entry save(Entry entry) {
        if (entry.getId() == 0) {
            entry.setId(++countId);
        }
        entries.add(entry);
        return entry;
    }

    @Override
    public List<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry findById(int id) {
        for(Entry entry: entries){
            if(entry.getId() == id){
                return entry;
            }
        }
        return null;
    }

    @Override
    public Entry findByAuthor() {
        return null;
    }

    @Override
    public long count() {

        return entries.size();
    }

    @Override
    public void delete(int id) {
        Entry  entry = findById(1);
        entries.remove(entry);
    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);

    }

    @Override
    public List<Entry> findTheAuthorStuff(String author) {
        List<Entry> authorEntries = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getAuthor().equalsIgnoreCase(author))
                authorEntries.add(entry);
        }
        return authorEntries;
    }
}
