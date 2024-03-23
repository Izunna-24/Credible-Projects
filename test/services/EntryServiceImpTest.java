package services;

import dearDiary.controllers.services.EntryServiceImp;
import dearDiary.controllers.dtos.CreateEntryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void entriesCanBeCreated_entriesCreatedArePresentInTheRepoTest() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setTitle("Coping");
        createEntryRequest.setBody("Survival is a must");
        createEntryRequest.setAuthor("Pascal");
        entryService.createEntry(createEntryRequest);

        createEntryRequest.setTitle("Tomcat");
        createEntryRequest.setBody("Apache Tomcat provides env for executing prog written in java");
        createEntryRequest.setAuthor("Izunna");
        entryService.createEntry(createEntryRequest);

        assertEquals(2,entryService.count());

    }



    @Test
    public void entriesInTheEntryServRepoCanBeFoundByAuthorTest() {
        assertEquals(0, entryService.count());

        
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