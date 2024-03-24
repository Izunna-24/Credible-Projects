package dearDiary.services;

import dearDiary.data.models.Entry;
import dearDiary.data.repositories.EntryRepository;
import dearDiary.data.repositories.EntryRepositoryImp;
import dearDiary.dtos.CreateEntryRequest;
import dearDiary.dtos.DeleteEntryRequest;
import dearDiary.dtos.UpdateEntryRequest;
import dearDiary.exceptions.EntryNotFoundException;

import java.util.List;

public class EntryServiceImp implements EntryService {

    private EntryRepository entryRepository = new EntryRepositoryImp();

    @Override
    public List<Entry> findByAuthor(String author) {

        return entryRepository.findByAuthor(author);
    }

    @Override
    public Entry findById(int id) {
        if (entryRepository.findById(id) == null) throw new EntryNotFoundException("Entry does not exist");
        return entryRepository.findById(id);

    }

    @Override
    public long count() {
        return entryRepository.count();
    }

    @Override
    public void createEntryWith(CreateEntryRequest createEntryRequest) {
        Entry entry = new Entry();
        entry.setTitle(createEntryRequest.getTitle());
        entry.setBody(createEntryRequest.getBody());
        entry.setAuthor(createEntryRequest.getAuthor());
        entryRepository.save(entry);
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest updateEntryRequest) {
        Entry entry = entryRepository.findById(updateEntryRequest.getId());
        if (entry == null) throw  new EntryNotFoundException("Entry Not Found");
        if (!entry.getAuthor().equalsIgnoreCase(updateEntryRequest.getAuthor())) throw new EntryNotFoundException("Entry not found");
        entryRepository.save(entry);
    }


    @Override
    public void deleteEntry(DeleteEntryRequest deleteRequest) {
        Entry entry = entryRepository.findById(deleteRequest.getId());
        if (entry == null) throw new EntryNotFoundException("Entry Not Found");
        entryRepository.delete(entry);
    }

    @Override
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public void save(Entry entry) {
        entryRepository.save(entry);
    }

}