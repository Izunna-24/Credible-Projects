package services;

import data.models.Entry;
import dtos.CreateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> findByUsername(String username);
    Entry findById(int id);
    long count();
   void createEntry(CreateEntryRequest createEntryRequest);
   void updateEntry(Entry entry);
   void delete(String entryId);
}
