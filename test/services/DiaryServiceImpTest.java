package services;

import dtos.LoginRequest;
import dtos.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImpTest {
    private DiaryService diaryService;
    @BeforeEach
    public void givenAlways(){
        diaryService =  new DiaryServiceImp();
    }
    @Test
    public void diaryIsUnlocked_atCreationTest() {
        assertFalse(diaryService.isLocked());
    }

    @Test
    public void diaryIsUnlocked_diaryCanBeLockedTest(){
        assertFalse(diaryService.isLocked());
        diaryService.lockDiary();
        assertTrue(diaryService.isLocked());
    }

    @Test
    public void diaryIsLocked_UnlockWithPasswordTest(){
        assertTrue(diaryService.isLocked());
        LoginRequest login = new LoginRequest();
        diaryService.unLockWith(login);
        assertFalse(diaryService.isLocked());
    }


    @Test
    public void diaryCan_registerTest() {
        DiaryServiceImp diaryService = new DiaryServiceImp();
        RegisterRequest registerationDetails = new RegisterRequest();
        registerationDetails.setUsername("username");
        registerationDetails.setPassword("password");

        diaryService.registerWith(registerationDetails);

    }

}