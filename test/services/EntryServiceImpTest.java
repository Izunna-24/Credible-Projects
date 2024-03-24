package services;

import dearDiary.data.models.Entry;
import dearDiary.dtos.DeleteEntryRequest;
import dearDiary.dtos.UpdateEntryRequest;
import dearDiary.exceptions.EntryNotFoundException;
import dearDiary.services.EntryServiceImp;
import dearDiary.dtos.CreateEntryRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntryServiceImpTest {
private EntryServiceImp entryService;

@BeforeEach
public void provided(){
    entryService = new EntryServiceImp();
}

@AfterEach
public void defaultEntry(){
    }
    @Test
    public void entryCanBeCreatedTest() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setTitle("Coping");
        createEntryRequest.setBody("Survival is a must");
        createEntryRequest.setAuthor("Pascal");
        entryService.createEntryWith(createEntryRequest);
        assertEquals(1,entryService.count());

    }
    @Test
    public void entriesCreatedArePresentInTheRepoTest() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
        createEntryRequest.setTitle("Coping");
        createEntryRequest.setBody("Survival is a must");
        createEntryRequest.setAuthor("Pascal");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Tomcat");
        createEntryRequest.setBody("Apache Tomcat provides env for executing prog written in java");
        createEntryRequest.setAuthor("Izunna");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2,entryService.count());

    }



    @Test
    public void entryInThe_entryServRepo_canBeFoundByAuthorTest() {
        assertEquals(0, entryService.count());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("http methods");
        createEntryRequest.setBody("get, post, delete, patch, put");
        createEntryRequest.setAuthor("Izunna");

        entryService.createEntryWith(createEntryRequest);

        assertEquals(1, entryService.count());

        entryService.findByAuthor("Izunna");

        List<Entry> confirmEntry = new ArrayList<>(Arrays.asList(entryService.findById(1)));

        assertEquals(confirmEntry, entryService.findByAuthor("Izunna"));

    }

    @Test
    public void entriesInThe_entryServRepo_canBeFoundByAuthorTest(){
        assertEquals(0, entryService.count());
    CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Database");
        createEntryRequest.setBody("mostly used as storage because it's persistent ability");
        createEntryRequest.setAuthor("Fidel");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("MongoDB");
        createEntryRequest.setBody("Type of DB");
        createEntryRequest.setAuthor("Tom");
        entryService.createEntryWith(createEntryRequest);


        createEntryRequest.setTitle("SQL");
        createEntryRequest.setBody("Structured Query Language");
        createEntryRequest.setAuthor("Bill");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(3, entryService.count());

        List<Entry> entriesMade = new ArrayList<>(Arrays.asList(entryService.findById(1),
                entryService.findById(2),
        entryService.findById(3)));

        assertEquals(entriesMade, entryService.findAllEntries());


    }
    @Test
    public void createdEntries_canBeDeletedTest() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Village Setting");
        createEntryRequest.setBody("Tripod Stand, hanging a big silver pot, turned dark from years of coal burning");
        createEntryRequest.setAuthor("Folake");

        entryService.createEntryWith(createEntryRequest);
        assertEquals(1, entryService.count());

        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();
        deleteRequest.setId(1);
        deleteRequest.setAuthor("Folake");

        entryService.deleteEntry(deleteRequest);

        assertEquals(0, entryService.count());
    }
        @Test
        public void createdTwoEntries_deleteOne_oneStillRemainsTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bonny Island");
        createEntryRequest.setBody("Island where fishes and crude oil live in peace");
        createEntryRequest.setAuthor("Shirley Brown");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Games");
        createEntryRequest.setBody("Its all games till you are at the loosing end");
        createEntryRequest.setAuthor("Izunna");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2,entryService.count());

        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();
        deleteRequest.setId(2);
        deleteRequest.setAuthor("Izunna");
        entryService.deleteEntry(deleteRequest);


        assertEquals(1, entryService.count());

    }

    @Test
    public void deleting_nonExistentEntry_throwsEntryDoesNotExistExceptionTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bonny Island");
        createEntryRequest.setBody("Island where fishes and crude oil live in peace");
        createEntryRequest.setAuthor("Shirley Brown");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Games");
        createEntryRequest.setBody("Its all games till you are at the loosing end");
        createEntryRequest.setAuthor("Izunna");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2,entryService.count());

        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();
        deleteRequest.setId(3);
        deleteRequest.setAuthor("Another");

        assertThrows(EntryNotFoundException.class, ()-> entryService.deleteEntry(deleteRequest));

    }

//    @Test
//    public void entryAuthorMismatchWhileDeleting_throwsEntryNotFoundExceptionTest(){
//        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
//
//        createEntryRequest.setTitle("Bonny Island");
//        createEntryRequest.setBody("Island where fishes and crude oil live in peace");
//        createEntryRequest.setAuthor("Shirley Brown");
//        entryService.createEntryWith(createEntryRequest);
//
//        createEntryRequest.setTitle("Games");
//        createEntryRequest.setBody("Its all games till you are at the loosing end");
//        createEntryRequest.setAuthor("Izunna");
//        entryService.createEntryWith(createEntryRequest);
//
//        assertEquals(2,entryService.count());
//
//        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();
//        deleteRequest.setId(1);
//        deleteRequest.setAuthor("Izunna");
//
//        assertThrows(EntryNotFoundException.class, ()-> entryService.deleteEntry(deleteRequest));
//}
@Test
public void settingWrong_id_WhileDeleting_throwsEntryNotFoundExceptionTest() {
    CreateEntryRequest createEntryRequest = new CreateEntryRequest();

    createEntryRequest.setTitle("Bonny Island");
    createEntryRequest.setBody("Island where fishes and crude oil live in peace");
    createEntryRequest.setAuthor("Shirley Brown");
    entryService.createEntryWith(createEntryRequest);

    createEntryRequest.setTitle("Games");
    createEntryRequest.setBody("Its all games till you are at the loosing end");
    createEntryRequest.setAuthor("Izunna");
    entryService.createEntryWith(createEntryRequest);

    assertEquals(2, entryService.count());

    DeleteEntryRequest deleteRequest = new DeleteEntryRequest();
    deleteRequest.setId(3);
    deleteRequest.setAuthor("Shirley Brown");

    assertThrows(EntryNotFoundException.class, () -> entryService.deleteEntry(deleteRequest));

}
    @Test
    public void settingNonExistentId_WhileDeleting_throwsEntryNotFoundExceptionTest() {
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bonny Island");
        createEntryRequest.setBody("Island where fishes and crude oil live in peace");
        createEntryRequest.setAuthor("Shirley Brown");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Games");
        createEntryRequest.setBody("Its all games till you are at the loosing end");
        createEntryRequest.setAuthor("Izunna");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2, entryService.count());

        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();
        deleteRequest.setId(4);
        deleteRequest.setAuthor("Shirley Brown");

        assertThrows(EntryNotFoundException.class, () -> entryService.deleteEntry(deleteRequest));

    }

    @Test
    public void storedEntriesCanBe_updatedTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Creation");
        createEntryRequest.setBody("The art of creating something from nothing or something");
        createEntryRequest.setAuthor("Pauline");

        entryService.createEntryWith(createEntryRequest);

        assertEquals(1,entryService.count());
        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setId(1);
        updateEntryRequest.setAuthor("Pauline");

        entryService.updateEntryWith(updateEntryRequest);
 }
 @Test
    public void updatingStoredEntryWithWrongAuthorName_throwsEntryNotExistTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Creation");
        createEntryRequest.setBody("The art of creating something from nothing or something");
        createEntryRequest.setAuthor("Pauline");

        entryService.createEntryWith(createEntryRequest);

        assertEquals(1,entryService.count());
        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setId(1);
        updateEntryRequest.setAuthor("Pauly");

        assertThrows(EntryNotFoundException.class, ()-> entryService.updateEntryWith(updateEntryRequest));
 }

    @Test
    public void updatingStoredEntryWithWrong_id_throwsEntryNotExistTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Creation");
        createEntryRequest.setBody("The art of creating something from nothing or something");
        createEntryRequest.setAuthor("Pauline");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Jupiter");
        createEntryRequest.setBody("One of the planet and also a god!");
        createEntryRequest.setAuthor("Hardy Forks");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2,entryService.count());

        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setId(2);
        updateEntryRequest.setAuthor("Pauline");

        assertThrows(EntryNotFoundException.class, ()-> entryService.updateEntryWith(updateEntryRequest));
    }

    @Test
    public void updatingStoredEntryWithNonExistence_id_and_Author_throwsEntryNotExistTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Creation");
        createEntryRequest.setBody("The art of creating something from nothing or something");
        createEntryRequest.setAuthor("Pauline");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Jupiter");
        createEntryRequest.setBody("One of the planet and also a god!");
        createEntryRequest.setAuthor("Hardy Forks");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2,entryService.count());

        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setId(6);
        updateEntryRequest.setAuthor("Palms Green");

        assertThrows(EntryNotFoundException.class, ()-> entryService.updateEntryWith(updateEntryRequest));
    }
    @Test
    public  void findingEntryWithWrong_id_throwsEntryNotFoundExceptionTest(){
        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Creation");
        createEntryRequest.setBody("The art of creating something from nothing or something");
        createEntryRequest.setAuthor("Pauline");
        entryService.createEntryWith(createEntryRequest);

        createEntryRequest.setTitle("Jupiter");
        createEntryRequest.setBody("One of the planet and also a god!");
        createEntryRequest.setAuthor("Hardy Forks");
        entryService.createEntryWith(createEntryRequest);

        assertEquals(2,entryService.count());

        assertThrows(EntryNotFoundException.class, ()->entryService.findById(5));

    }

}