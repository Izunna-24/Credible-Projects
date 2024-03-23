package dearDiary.controllers.services;

import dearDiary.controllers.data.models.Diary;
import dearDiary.controllers.data.models.Entry;
import dearDiary.controllers.data.repositories.DiaryRepository;
import dearDiary.controllers.data.repositories.DiaryRepositoryImp;
import dearDiary.controllers.data.repositories.EntryRepository;
import dearDiary.controllers.data.repositories.EntryRepositoryImp;
import dearDiary.controllers.dtos.CreateEntryRequest;
import dearDiary.controllers.dtos.LoginRequest;
import dearDiary.controllers.dtos.RegisterRequest;
import dearDiary.controllers.exceptions.DiaryDoesNotExistException;
import dearDiary.controllers.exceptions.InvalidInputException;
import dearDiary.controllers.exceptions.UsernameDoesNotExistException;
import dearDiary.controllers.exceptions.UsernameExitsException;

import java.util.List;

public class DiaryServiceImp implements DiaryService{
    private DiaryRepository diaryRepo = new DiaryRepositoryImp();
    private EntryService entryService = new EntryServiceImp();
    private EntryRepository entryRepository = new EntryRepositoryImp();

    @Override
    public void registerWith(RegisterRequest request) {
        validateRegistration(request);
         Diary diary = new Diary();
         diary.setUsername(request.getUsername());
         diary.setPassword(request.getPassword());
         diaryRepo.save(diary);
    }

    private void validateRegistration(RegisterRequest request) {
        if (request.getUsername() == null || request.getPassword() == null ){
            throw new InvalidInputException("Wrong login Details");}
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
        throw new InvalidInputException("Wrong login details ");
        }
        if(diaryRepo.findById(request.getUsername()) != null){
            throw new UsernameExitsException("Username not available");
        }
    }

    @Override
    public Diary findById(java.lang.String username) {
        Diary diary = diaryRepo.findById(username);
        if (diary == null) {throw new DiaryDoesNotExistException("Diary not found");
            }
         return diary;

    }

    @Override
    public List<Entry> findAllEntries(String username) {
        return entryService.findByAuthor(username);
    }



    @Override
    public long count() {
        return diaryRepo.count();
    }

    @Override
    public void loginWith(LoginRequest loginRequest) {
        Diary diary = findById(loginRequest.getUsername());
        validateLogin(loginRequest, diary);
        diary.setLocked(false);
        diaryRepo.save(diary);
    }

    private static void validateLogin(LoginRequest loginRequest, Diary diary) {
        if (!diary.getPassword().equals(loginRequest.getPassword())){
            throw new UsernameDoesNotExistException("Invalid Entry");
        }
    }

    @Override
    public void logout(String username) {
        Diary diary = findById(username);
        diary.setLocked(true);
        diaryRepo.save(diary);
    }

    @Override
    public void delete(RegisterRequest request) {
    //
    }

    @Override
    public void createEntry(CreateEntryRequest createEntry) {
        Entry entry = new Entry();
        entry.setTitle(createEntry.getTitle());
        entry.setBody(createEntry.getBody());
        entry.setAuthor(createEntry.getAuthor());
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findByTheAuthor(String username) {
        return entryRepository.findTheAuthorStuff(username);
    }

}
