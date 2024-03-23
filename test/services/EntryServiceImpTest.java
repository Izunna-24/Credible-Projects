package services;

import dearDiary.data.models.Entry;
import dearDiary.services.EntryServiceImp;
import dearDiary.dtos.CreateEntryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void entriesInThe_entryServRepo_canBeFoundByAuthorTest() {
        assertEquals(0, entryService.count());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setTitle("Route/Port Number");
        createEntryRequest.setBody("Local access for a DB");
        createEntryRequest.setAuthor("Jim");
        entryService.createEntry(createEntryRequest);

        createEntryRequest.setTitle("http methods");
        createEntryRequest.setBody("get, post, delete, patch, put");
        createEntryRequest.setAuthor("Izunna");
        entryService.createEntry(createEntryRequest);
        assertEquals(2, entryService.count());
        entryService.findByAuthor("Jim");
        List<Entry> confirmEntry = new ArrayList<>(Arrays.asList(findById(1), ));
        assertEquals();




        
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