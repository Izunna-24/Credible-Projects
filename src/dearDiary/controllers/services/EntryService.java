package dearDiary.controllers.services;

import dearDiary.controllers.data.models.Entry;
import dearDiary.controllers.dtos.CreateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> findByAuthor(String username);
    Entry findById(int id);
    long count();
   void createEntry(CreateEntryRequest createEntryRequest);
   void updateEntry(Entry entry);
   void delete(String entryId);
}
