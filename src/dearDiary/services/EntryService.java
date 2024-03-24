package dearDiary.services;

import dearDiary.data.models.Entry;
import dearDiary.dtos.CreateEntryRequest;
import dearDiary.dtos.DeleteEntryRequest;
import dearDiary.dtos.UpdateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> findByAuthor(String author);
    Entry findById(int id);
    long count();
   void createEntryWith(CreateEntryRequest createEntryRequest);
   void updateEntryWith(UpdateEntryRequest updateEntry);
   void deleteEntry(DeleteEntryRequest deleteRequest);
   List<Entry> findAllEntries();

    void save(Entry entry);
}
