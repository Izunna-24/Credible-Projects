package services;

import data.models.Diary;
import dtos.CreateEntryRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;

import java.util.List;

public interface DiaryService {
    void registerWith(RegisterRequest register);
    Diary findById(String id);
    List<Diary> findAll();
    void lockDiary();
    long count();
    void login(LoginRequest username, LoginRequest password);
    void lockWith(LoginRequest password);

    boolean isLocked();
    void remove(RegisterRequest request);
    void unLockWith(LoginRequest unlock);
    void CreateEntry(CreateEntryRequest createEntry);

}
