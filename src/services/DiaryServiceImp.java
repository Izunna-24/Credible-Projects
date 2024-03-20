package services;

import data.models.Diary;
import data.models.Entry;
import data.repositories.DiaryRepository;
import data.repositories.DiaryRepositoryImp;
import dtos.CreateEntryRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.DiaryDoesNotExistException;
import exceptions.InvalidInputException;
import exceptions.UsernameDoesNotExistException;
import exceptions.UsernameExitsException;

import java.util.List;

public class DiaryServiceImp implements DiaryService{
    private DiaryRepository diaryRepo = new DiaryRepositoryImp();
    private EntryService entryService = new EntryServiceImplementation();

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
        return entryService.findByUsername(username);
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
    public void remove(RegisterRequest request) {

    }

    @Override
    public void createEntry(CreateEntryRequest createEntry) {
        Entry entry = new Entry();
        entry.setTitle(createEntry.getTitle());
        entry.setBody(createEntry.getBody());
        entry.setAuthor(createEntry.getAuthor());
        entryService.save(entry);
    }

}
