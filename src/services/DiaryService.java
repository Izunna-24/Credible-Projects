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
    boolean isLocked();
    long count();
    void login(LoginRequest loginRequest);
    void logout(LoginRequest loginRequest);

    void remove(RegisterRequest request);
    void CreateEntry(CreateEntryRequest createEntry);

}
