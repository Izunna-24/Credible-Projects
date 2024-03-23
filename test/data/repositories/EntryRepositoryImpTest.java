package data.repositories;

import dearDiary.data.models.Entry;
import dearDiary.data.repositories.EntryRepositoryImp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImpTest {

    @Test
    public void entryIsCreatedAndSavedInTheRepoTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry entry = new Entry("title","body");
        repo.save(entry);
        assertEquals(1, repo.count());

    }
    @Test
    public void twoEntriesAreCreatedAndSavedInTheRepo_twoEntriesAreFoundTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry entry = new Entry("title","body");
        Entry ghost = new Entry("mode","Active, but you can't see me!");

        repo.save(entry);
        repo.save(ghost);

        assertEquals(2, repo.count());

    }

    @Test
    public void twoEntries_secondEntryCanBeFoundByIdTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry firstEntry = new Entry("Jeans","Difference between baggie jeans and skinny jeans is age");
        Entry myFacts = new Entry("Hope","We live off hope or not");
        repo.save(firstEntry);
        repo.save(myFacts);
        assertEquals(2, repo.count());
        assertEquals(myFacts, repo.findById(2));
    }

    @Test
    public void twoEntries_firstEntryCanBeFoundByIdTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry firstEntry = new Entry("Jeans","Difference between baggie jeans and skinny jeans is age");
        Entry myFacts = new Entry("Hope","We live off hope or not");
        repo.save(firstEntry);
        repo.save(myFacts);
        assertEquals(2, repo.count());
        assertEquals(firstEntry, repo.findById(1));
    }



    @Test
    public void entriesInTheRepoCanBeCountedTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry entry = new Entry();
        repo.save(entry);
        assertEquals(1, repo.count());
    }

    @Test
    public void savedEntriesCanBeDeletedByIdTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry entry = new Entry();
        repo.save(entry);
        repo.delete(entry.getId());
       assertEquals(0,repo.count());
    }
    @Test
    public void doubleEntriesAreSaved_oneEntryIsDeletedByIdTest() {
        EntryRepositoryImp repo = new EntryRepositoryImp();
        Entry entry = new Entry();
        Entry loveLife = new Entry("Love","No true love, we are just using each other");

        repo.save(entry);
        repo.save(loveLife);

        repo.delete(entry.getId());
       assertEquals(1,repo.count());
    }

   @Test
   public void doubleEntriesSaved_bothCanBeDeletedByEntryTest(){
       EntryRepositoryImp repo = new EntryRepositoryImp();
       Entry scum = new Entry("Crest","Getting to the crest sounds interesting until you try");
       Entry dame = new Entry("Abigail","You haven't felt love until you meet my mother; Abigail );");
       repo.save(scum);
       repo.save(dame);

       repo.delete(scum);
       repo.delete(dame);
       assertEquals(0, repo.count());
   }
  @Test
   public void doubleEntriesAreCreated_doubleEntriesCanBeFoundTest(){
       EntryRepositoryImp repo = new EntryRepositoryImp();
       Entry entry = new Entry("Crest","Getting to the crest sounds interesting until you try");
       Entry newEntry = new Entry("Case","Justice is not emotional");

      List<Entry> myEntries = new ArrayList<>(Arrays.asList(entry, newEntry));
      repo.save(entry);
      repo.save(newEntry);
      assertEquals(myEntries, repo.findAll());
   }
   @Test
   public void oneEntryIsCreated_oneEntryCanBeFoundTest(){
       EntryRepositoryImp repo = new EntryRepositoryImp();
       Entry adaptation = new Entry("Learning","Never ending, never stopping");


      List<Entry> myEntries = new ArrayList<>(List.of(adaptation));
      repo.save(adaptation);
      assertEquals(myEntries, repo.findAll());
   }

}