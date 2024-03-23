package data.repositories;

import dearDiary.data.models.Diary;
import dearDiary.data.repositories.DiaryRepositoryImp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImpTest {

    @Test
    public void diaryCanBeSavedInRepositoryTest() {
        DiaryRepositoryImp repository = new DiaryRepositoryImp();
        Diary diary = new Diary("username","password");
        repository.save(diary);
        assertEquals(1L,repository.count());
    }
    @Test
    public void doubleDiaryCanBeSavedInRepositoryTest() {
        DiaryRepositoryImp repository = new DiaryRepositoryImp();
        Diary cornerBoy = new Diary("username","password");
        Diary cornerOffice = new Diary("username","password");
        repository.save(cornerBoy);
        repository.save(cornerOffice);
        assertEquals(2L,repository.count());
    }

    @Test
    public void diaryCanBe_foundById_inTheRepoTest(){
        DiaryRepositoryImp repository = new DiaryRepositoryImp();
        Diary diary = new Diary("username","password");
        Diary newDiary = new Diary("username1","password");
        repository.save(diary);
        repository.save(newDiary);
        repository.findById("username1");
        assertEquals(newDiary, repository.findById("username1"));

    }

    @Test
    public void doubleSavedDiariesCanBe_foundById_inTheRepoTest(){
        DiaryRepositoryImp repository = new DiaryRepositoryImp();
        Diary diary = new Diary("username","password");
        Diary newDiary = new Diary("username1","password");
        repository.save(diary);
        repository.save(newDiary);

        repository.findById("username1");
        assertEquals(newDiary, repository.findById("username1"));

        repository.findById(diary.getUsername());
        assertEquals(diary, repository.findById("username"));


    }


    @Test
    public void doubleDiarySaved_oneDeleted_oneRemainsInTheRepoByIndexTest(){
        DiaryRepositoryImp repo = new DiaryRepositoryImp();
        Diary diary1 = new Diary("username","password1");
        Diary diary2 = new Diary("username","password2");

        repo.save(diary1);
        repo.save(diary2);
        assertEquals(2,repo.count());

        repo.delete(diary2.getUsername());
        assertEquals(1, repo.count());
    }
    @Test
    public void diarySaved_diaryDeleted_DiaryIsDeletedByIndexTest(){
        DiaryRepositoryImp repo = new DiaryRepositoryImp();
        Diary company = new Diary("username","password1");

        repo.save(company);
        assertEquals(1,repo.count());

        repo.delete(company.getUsername());
        assertEquals(0, repo.count());
    }

    @Test
    public void diariesSaved_areFoundInTheRepoTest(){
        DiaryRepositoryImp repo = new DiaryRepositoryImp();
        Diary thompson = new Diary("user_thompson","password");
        Diary cabinCrew = new Diary("crewMember","passcode");
        Diary roadShow = new Diary("show_pass","pass");

        List<Diary> myDiaryCollection = new ArrayList<>(Arrays.asList(thompson,cabinCrew,roadShow));

        repo.save(thompson);
        repo.save(cabinCrew);
        repo.save(roadShow);
        assertEquals(myDiaryCollection, repo.findAll());
    }


@Test
public void diaryCanBeDeletedInTheRepoTest(){
        DiaryRepositoryImp repo = new DiaryRepositoryImp();
        Diary dittoCollection = new Diary("username","password1");
        Diary oppressionZero = new Diary("username","password2");

        repo.save(dittoCollection);
        repo.save(oppressionZero);
        assertEquals(2,repo.count());

        repo.delete(oppressionZero);
        assertEquals(1, repo.count());
    }


}