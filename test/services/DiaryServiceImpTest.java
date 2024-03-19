package services;

import dtos.LoginRequest;
import dtos.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiaryServiceImpTest {
    private DiaryService diaryService;
    @BeforeEach
    public void givenAlways(){
        diaryService =  new DiaryServiceImp();
    }
    @Test
    public void diaryIsLocked_atCreationTest() {

        diaryService.isLocked();
    }

    @Test
    public void diaryIsUnlocked_diaryCanBeLockedTest(){
        assertFalse(diaryService.isLogin());
        diaryService.isLocked();
        assertTrue(diaryService.isLogin());
    }

    @Test
    public void diaryIsLocked_UnlockWithPasswordTest(){
        assertTrue(diaryService.isLogin());
        LoginRequest login = new LoginRequest();
        login.setPassword("correctPassword");
        diaryService.unLockWith(login);
        assertFalse(diaryService.isLogin());
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