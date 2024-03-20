package services;

import data.models.Diary;
import data.models.Entry;
import dtos.CreateEntryRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;

import java.util.List;

public interface DiaryService {
    void registerWith(RegisterRequest register);
    Diary findById(java.lang.String id);
    List<Entry> findAllEntries(String username);
    long count();
    void loginWith(LoginRequest loginRequest);
    void logout(String loginRequest);

    void remove(RegisterRequest request);
    void createEntry(CreateEntryRequest createEntry);

}
