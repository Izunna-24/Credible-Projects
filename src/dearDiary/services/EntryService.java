package dearDiary.services;

import dearDiary.data.models.Entry;
import dearDiary.dtos.CreateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> findByAuthor(String username);
    Entry findById(int id);
    long count();
   void createEntry(CreateEntryRequest createEntryRequest);
   void updateEntry(Entry entry);
   void delete(String entryId);
}
