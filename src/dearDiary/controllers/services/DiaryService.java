package dearDiary.controllers.services;

import dearDiary.controllers.data.models.Diary;
import dearDiary.controllers.data.models.Entry;
import dearDiary.controllers.dtos.CreateEntryRequest;
import dearDiary.controllers.dtos.LoginRequest;
import dearDiary.controllers.dtos.RegisterRequest;

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
