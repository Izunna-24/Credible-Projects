package services;

import data.models.Diary;
import dtos.CreateEntryRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;

import java.util.List;

public class DiaryServiceImp implements DiaryService{
    private boolean isLocked = false;
    @Override
    public void registerWith(RegisterRequest request) {


    }

    @Override
    public Diary findById(String id) {
        return null;
    }

    @Override
    public List<Diary> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void login(LoginRequest password) {

    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void remove(RegisterRequest request) {

    }

    @Override
    public void unlock(LoginRequest unlock) {

    }

    @Override
    public void CreateEntry(CreateEntryRequest createEntry) {

    }
}
