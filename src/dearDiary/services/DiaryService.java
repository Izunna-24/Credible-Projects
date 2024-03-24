package dearDiary.services;

import dearDiary.data.models.Diary;
import dearDiary.data.models.Entry;
import dearDiary.dtos.*;

import java.util.List;

public interface DiaryService {
    void registerWith(RegisterRequest register);
    Diary findById(String username);
    List<Entry> findAllEntries(String username);
    long count();
    void loginWith(LoginRequest loginRequest);
    void logout(String loginRequest);
    void updateEntry(UpdateEntryRequest updateEntryRequest);
    void deleteEntry(DeleteEntryRequest deleteRequest);
    void createEntryWith(CreateEntryRequest createEntry);
    List<Entry> findEntriesByAuthor(String author);

    void deleteDiary(DeleteEntryRequest deleteRequest);
}
