package services;

import data.models.Entry;
import dtos.CreateEntryRequest;

import java.util.List;

public class EntryServiceImplementation implements EntryService{
    @Override
    public List<Entry> findAll() {
        return null;
    }

    @Override
    public Entry findByUsername(String username) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Entry findByDate() {
        return null;
    }

    @Override
    public Entry findByTitle(String title) {
        return null;
    }

    @Override
    public void createEntry(CreateEntryRequest createEntry) {

    }

    @Override
    public void updateEntry(CreateEntryRequest updateEntry) {

    }

    @Override
    public void delete(String entryId) {

    }
}
