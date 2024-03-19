package services;

import dtos.LoginRequest;
import dtos.RegisterRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImpTest {
    @Test
    public void diaryIsUnlocked_atCreationTest() {
        DiaryServiceImp diaryService = new DiaryServiceImp();
        assertFalse(diaryService.isLocked());
    }

    @Test
    public void diaryIsUnlocked_diaryCanBeLockedTest(){

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