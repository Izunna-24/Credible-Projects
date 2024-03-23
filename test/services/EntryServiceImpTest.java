package services;

import data.models.Entry;
import dtos.CreateEntryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryServiceImpTest {
private EntryServiceImp entryService;

@BeforeEach
public void provided(){
    entryService = new EntryServiceImp();
}


    @Test
    public void entryCanBeCreatedTest() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setTitle("Coping");
        createEntryRequest.setBody("Survival is a must");
        createEntryRequest.setAuthor("Pascal");
        entryService.createEntry(createEntryRequest);
        assertEquals(1,entryService.count());

    }

    @Test
    public void testThat_entryCanBeFoundByUsername() {
        List<Entry> entries = new ArrayList<>();
        EntryServiceImp entryServiceImplementation = new EntryServiceImp();
        Entry entry = new Entry();
        entries.add(entry);

    }

    @Test
    void findById() {
    }

    @Test
    void count() {
    }


    @Test
    void updateEntry() {
    }

    @Test
    void delete() {
    }
}