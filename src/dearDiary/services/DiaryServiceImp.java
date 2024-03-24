package dearDiary.services;

import dearDiary.data.models.Diary;
import dearDiary.data.models.Entry;
import dearDiary.data.repositories.DiaryRepository;
import dearDiary.data.repositories.DiaryRepositoryImp;
import dearDiary.dtos.*;
import dearDiary.exceptions.*;

import java.util.List;

public class DiaryServiceImp implements DiaryService{
    private DiaryRepository diaryRepo = new DiaryRepositoryImp();
    private EntryService entryService = new EntryServiceImp();


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
            throw new UsernameAlreadyExitsException("Username not available");
        }
    }

    @Override
    public Diary findById(String username) {
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
            throw new WrongPasswordException("Check your details and try again");
        }
        if (!diary.getUsername().equalsIgnoreCase(loginRequest.getUsername())){
            throw new WrongUserNameException("Check your details and try again");
        }

    }
    @Override
    public void logout(String username) {
        Diary diary = findById(username);
        diary.setLocked(true);
        diaryRepo.save(diary);
    }

    @Override
    public void updateEntry(UpdateEntryRequest updateEntryRequest) {
    Entry entry = entryService.findById(updateEntryRequest.getId());
    if (entry == null) {throw new EntryNotFoundException("Entry not found");}
    Diary diary = diaryRepo.findById(updateEntryRequest.getAuthor());
    if (diary == null) {throw new DiaryDoesNotExistException("Diary does not exist");}
    entryService.save(entry);
    }

    @Override
    public void deleteEntry(DeleteEntryRequest deleteRequest) {
    Diary diary = diaryRepo.findById(deleteRequest.getAuthor());
    if (diary == null) throw new EntryNotFoundException("Diary does not exist");
    entryService.deleteEntry(deleteRequest);
    }

    @Override
    public void createEntryWith(CreateEntryRequest createEntry) {
        Entry entry = new Entry();
        entry.setTitle(createEntry.getTitle());
        entry.setBody(createEntry.getBody());
        entry.setAuthor(createEntry.getAuthor());
        entryService.save(entry);
    }

    @Override
    public List<Entry> findEntriesByAuthor(String username) {
        return entryService.findByAuthor(username);
    }

    @Override
    public void deleteDiary(DeleteEntryRequest deleteRequest) {
        Diary diary = diaryRepo.findById(deleteRequest.getAuthor());
        if (diary == null) throw new DiaryDoesNotExistException("Diary does not exist");
        diaryRepo.delete(diary);
    }

}
