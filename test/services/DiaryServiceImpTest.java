package services;

import data.models.Diary;
import dtos.CreateEntryRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        diaryService.createEntry(createEntryRequest);

        System.out.println(diaryService.findAllEntries("Mandy"));


        assertEquals(1, diaryService.findByTheAuthor("Mandy").size());
    }
}