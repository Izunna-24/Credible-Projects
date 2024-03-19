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
    long count();
    void login(LoginRequest password);

    boolean isLocked();
    void remove(RegisterRequest request);
    void unlock(LoginRequest unlock);
    void CreateEntry(CreateEntryRequest createEntry);

}
