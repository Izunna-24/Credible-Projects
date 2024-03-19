package services;

import data.models.Diary;
import data.repositories.DiaryRepository;
import data.repositories.DiaryRepositoryImp;
import dtos.CreateEntryRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.DiaryDoesNotExistException;
import exceptions.UsernameDoesNotExistException;

import java.util.List;

public class DiaryServiceImp implements DiaryService{
    private DiaryRepository diaryRepo = new DiaryRepositoryImp();
    private boolean isLocked;
    @Override
    public void registerWith(RegisterRequest request) {


    }

    @Override
    public Diary findById(String username) {
        Diary diary = diaryRepo.findById(username);
        if (diary == null) {throw new DiaryDoesNotExistException("Diary not found");
            }
         return diary;

    }

    @Override
    public List<Diary> findAll() {
        return null;
    }

    @Override
    public void lockDiary() {
        isLocked = true;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Diary diary = findById(loginRequest.getUsername());
        if (!diary.getPassword().equals(loginRequest.getPassword())){
            throw new UsernameDoesNotExistException("Invalid Entry");
        }
        isLocked = false;
    }

    @Override
    public void lockWith(LoginRequest password) {

    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void remove(RegisterRequest request) {

    }

    @Override
    public void unLockWith(LoginRequest password) {
//        if(!this.)

    }

    @Override
    public void CreateEntry(CreateEntryRequest createEntry) {

    }
}
