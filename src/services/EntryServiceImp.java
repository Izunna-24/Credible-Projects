package services;

import data.models.Entry;
import data.repositories.EntryRepository;
import data.repositories.EntryRepositoryImp;
import dtos.CreateEntryRequest;

import java.util.ArrayList;
import java.util.List;

public class EntryServiceImp implements EntryService {

    private EntryRepository entryRepository = new EntryRepositoryImp();
    @Override
    public List<Entry> findByAuthor(String username) {
        List<Entry> entries = new ArrayList<>();
        for (Entry entry : entryRepository.findAll()) {
            if (entry.getAuthor().equalsIgnoreCase(username)) {
                entries.add(entry);
            }
        }
        return entries;
    }

    @Override
    public Entry findById(int id) {
        return null;
    }

    @Override
    public long count() {
        return entryRepository.count();
    }

    @Override
    public void createEntry(CreateEntryRequest createEntryRequest) {
        Entry entry = new Entry();
        entry.setTitle(createEntryRequest.getTitle());
        entry.setBody(createEntryRequest.getBody());
        entry.setAuthor(createEntryRequest.getAuthor());
        entryRepository.save(entry);
    }

    @Override
    public void updateEntry(Entry entry) {

    }


    @Override
    public void delete(String entryId) {

    }
}