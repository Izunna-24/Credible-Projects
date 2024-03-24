package services;

import dearDiary.data.models.Diary;
import dearDiary.data.models.Entry;
import dearDiary.dtos.*;
import dearDiary.exceptions.DiaryDoesNotExistException;
import dearDiary.exceptions.EntryNotFoundException;
import dearDiary.exceptions.WrongPasswordException;
import dearDiary.exceptions.WrongUserNameException;
import dearDiary.services.DiaryServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImpTest {
    private DiaryServiceImp diaryService;
    @BeforeEach
    public void givenAlways(){
        diaryService =  new DiaryServiceImp();
    }

    @Test
    public  void diaryCanBeCreatedTest(){
        RegisterRequest registrationProcess = new RegisterRequest();
        registrationProcess.setPassword("password");
        registrationProcess.setUsername("username");

        diaryService.registerWith(registrationProcess);
        assertEquals(1,diaryService.count());

    }
    @Test
    public void diaryIsLockedAtCreationTest() {
        RegisterRequest register = new RegisterRequest();

        register.setUsername("August");
        register.setPassword("password");
        diaryService.registerWith(register);

        Diary diary = diaryService.findById(register.getUsername());
        assertTrue(diary.isLocked());
    }

    @Test
    public void diaryIsLocked_whileLoggedOutTest(){
        RegisterRequest register = new RegisterRequest();

        register.setUsername("Bunkers!");
        register.setPassword("password");
        diaryService.registerWith(register);

        Diary diary = diaryService.findById("Bunkers!");
        LoginRequest loginRequest =  new LoginRequest();
        loginRequest.setUsername("Bunkers!");
        loginRequest.setPassword("password");

        diaryService.loginWith(loginRequest);

        assertFalse(diary.isLocked());
        diaryService.logout("Bunkers!");
        assertTrue(diary.isLocked());
    }

    @Test
    public void entryCanBeCreated_inDiaryTest(){
        RegisterRequest registrationProcess = new RegisterRequest();
        registrationProcess.setPassword("password");
        registrationProcess.setUsername("Mandy");

        diaryService.registerWith(registrationProcess);

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bumpy Ride");
        createEntryRequest.setBody("Its been crazy, dear diary");
        createEntryRequest.setAuthor("Mandy");
        diaryService.createEntryWith(createEntryRequest);

        assertEquals(1, diaryService.findEntriesByAuthor("Mandy").size());
    }
    @Test
    public void entryCreated_canBeDeletedInDiaryTest(){
        RegisterRequest registrationProcess = new RegisterRequest();

        registrationProcess.setPassword("password");
        registrationProcess.setUsername("Mandy");
        diaryService.registerWith(registrationProcess);
        assertEquals(1, diaryService.count());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bumpy Ride");
        createEntryRequest.setBody("Its been crazy, dear diary");
        createEntryRequest.setAuthor("Mandy");

        diaryService.createEntryWith(createEntryRequest);
        assertEquals(1, diaryService.findEntriesByAuthor("Mandy").size());

        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();

        deleteRequest.setId(1);
        deleteRequest.setAuthor("Mandy");


        diaryService.deleteEntry(deleteRequest);
        assertEquals(0, diaryService.findEntriesByAuthor("Mandy").size());



    }
    @Test
    public void diaryCreated_canBeDeletedTest(){
        RegisterRequest registrationProcess = new RegisterRequest();

        registrationProcess.setPassword("password");
        registrationProcess.setUsername("Mandy");
        diaryService.registerWith(registrationProcess);
        assertEquals(1, diaryService.count());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bumpy Ride");
        createEntryRequest.setBody("Its been crazy, dear diary");
        createEntryRequest.setAuthor("Mandy");

        diaryService.createEntryWith(createEntryRequest);
        assertEquals(1, diaryService.findEntriesByAuthor("Mandy").size());

        DeleteEntryRequest deleteRequest = new DeleteEntryRequest();

        deleteRequest.setId(1);
        deleteRequest.setAuthor("Mandy");

        diaryService.deleteDiary(deleteRequest);
        assertEquals(0, diaryService.count());

    }
    @Test
    public void entriesInTheDiary_can_be_Updated(){
        RegisterRequest registrationProcess = new RegisterRequest();

        registrationProcess.setPassword("password");
        registrationProcess.setUsername("Mandy");
        diaryService.registerWith(registrationProcess);
        assertEquals(1, diaryService.count());

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setTitle("Bumpy Ride");
        createEntryRequest.setBody("Its been crazy, dear diary");
        createEntryRequest.setAuthor("Mandy");

        diaryService.createEntryWith(createEntryRequest);
        assertEquals(1, diaryService.findEntriesByAuthor("Mandy").size());

        UpdateEntryRequest updateEntryRequest = new UpdateEntryRequest();

        updateEntryRequest.setAuthor("Mandy");
        updateEntryRequest.setId(1);
        diaryService.updateEntry(updateEntryRequest);

        assertEquals(1,diaryService.count());
    }

    @Test
    public void diaryIsLocked_loginWithWrongPasswordThrowsException(){
        RegisterRequest register = new RegisterRequest();

        register.setUsername("Bunkers!");
        register.setPassword("password");
        diaryService.registerWith(register);

        LoginRequest loginRequest =  new LoginRequest();
        loginRequest.setUsername("Bunkers!");
        loginRequest.setPassword("wrongPassword");

        assertThrows(WrongPasswordException.class,()-> diaryService.loginWith(loginRequest));


    }

//    @Test
//    public void diaryIsLocked_loginWithWrongUsernameThrowsException(){
//        RegisterRequest register = new RegisterRequest();
//
//        register.setUsername("Bunkers!");
//        register.setPassword("password");
//        diaryService.registerWith(register);
//
//        LoginRequest loginRequest =  new LoginRequest();
//        loginRequest.setUsername("WrongUsername!");
//        loginRequest.setPassword("password");
//
//        assertThrows(WrongUserNameException.class,()-> diaryService.loginWith(loginRequest));
//
//
//    }
    @Test
    public void deleteEmptyEntryThrowsException(){
        RegisterRequest register = new RegisterRequest();

        register.setUsername("Bunkers!");
        register.setPassword("password");
        diaryService.registerWith(register);

        LoginRequest loginRequest =  new LoginRequest();
        loginRequest.setUsername("Bunkers!");
        loginRequest.setPassword("password");

        DeleteEntryRequest deleteEntryRequest = new DeleteEntryRequest();
        deleteEntryRequest.setAuthor("Bunkers!");
        deleteEntryRequest.setId(1);

        assertThrows(EntryNotFoundException.class,()-> diaryService.deleteEntry(deleteEntryRequest));
    }

    @Test
    public  void deletingEmptyDiaryAccount_throwsException(){
        RegisterRequest register = new RegisterRequest();

        register.setUsername("Bunkers!");
        register.setPassword("password");
        diaryService.registerWith(register);

        LoginRequest loginRequest =  new LoginRequest();
        loginRequest.setUsername("Bunkers!");
        loginRequest.setPassword("password");

        DeleteEntryRequest deleteEntryRequest = new DeleteEntryRequest();
        deleteEntryRequest.setAuthor("Bunkers");
        deleteEntryRequest.setId(1);

        assertThrows(DiaryDoesNotExistException.class, ()-> diaryService.deleteDiary(deleteEntryRequest));
    }

    @Test
    public void entryCanBeFoundByAuthorTest(){
        RegisterRequest register = new RegisterRequest();

        register.setUsername("Bunkers!");
        register.setPassword("password");
        diaryService.registerWith(register);

        LoginRequest loginRequest =  new LoginRequest();
        loginRequest.setUsername("Bunkers!");
        loginRequest.setPassword("password");

        CreateEntryRequest createEntryRequest = new CreateEntryRequest();

        createEntryRequest.setAuthor("Custom");
        createEntryRequest.setTitle("Topical Issues");
        createEntryRequest.setBody("Talk about the lgtbq+ community");

        diaryService.createEntryWith(createEntryRequest);


        assertEquals("Topical Issues",diaryService.findEntriesByAuthor("Custom").getFirst().getTitle());




    }
}