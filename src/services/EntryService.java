package services;

import data.models.Entry;
import dtos.CreateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> findAll();
    Entry findByUsername(String username);
    long count();
    Entry findByDate();
    Entry findByTitle(String title);
    void createEntry(CreateEntryRequest createEntry);
    void updateEntry(CreateEntryRequest updateEntry);
    void delete(String entryId);
}
