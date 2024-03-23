package dearDiary.services;

import dearDiary.data.models.Diary;
import dearDiary.data.models.Entry;
import dearDiary.dtos.CreateEntryRequest;
import dearDiary.dtos.LoginRequest;
import dearDiary.dtos.RegisterRequest;

import java.util.List;

public interface DiaryService {
    void registerWith(RegisterRequest register);
    Diary findById(java.lang.String id);
    List<Entry> findAllEntries(String username);
    long count();
    void loginWith(LoginRequest loginRequest);
    void logout(String loginRequest);

    void delete(RegisterRequest request);
    void createEntry(CreateEntryRequest createEntry);

    List<Entry> findByTheAuthor(String username);

}
