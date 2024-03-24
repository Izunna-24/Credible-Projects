package dearDiary.controllers;

import dearDiary.dtos.*;
import dearDiary.exceptions.DiaryExceptions;
import dearDiary.services.DiaryService;
import dearDiary.services.DiaryServiceImp;

public class DiaryController {
    private DiaryService diaryServices = new DiaryServiceImp();

    public String login(LoginRequest loginRequest) {

        try {
            diaryServices.loginWith(loginRequest);
            return String.format("%s has logged in successfully );", loginRequest.getUsername());
        }
        catch (DiaryExceptions e) {
            return e.getMessage();
        }

    }

    public String register(RegisterRequest registerRequest){
        try{
            diaryServices.registerWith(registerRequest);
            return String.format("Hello %s, your registration was successful!", registerRequest.getUsername());
        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }
    public String logout(String username){
        try {
            diaryServices.logout(username);
            return String.format("%s, you are currently logged out!", username);
        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }

    public  String createEntry(CreateEntryRequest createEntry){
        try{
            diaryServices.createEntryWith(createEntry);
            return String.format("Hello %s, your entry was created successfully", createEntry.getAuthor());
        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }
    public  String updateEntry(UpdateEntryRequest updateEntry){
        try{
            diaryServices.updateEntry(updateEntry);
            return String.format("Hello %s, your entry with id number %d has been created successfully%n", updateEntry.getAuthor(),updateEntry.getId());

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }
    public  String deleteEntry(DeleteEntryRequest deleteEntry){
        try{
            diaryServices.deleteEntry(deleteEntry);
            return String.format("Hello %s, your entry with id number %d has been deleted successfully%n", deleteEntry.getAuthor(), deleteEntry.getId());

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }
    public  String deleteDiary(DeleteEntryRequest deleteDiary){
        try{
            diaryServices.deleteDiary(deleteDiary);
            return String.format("Hello %s, your Diary account with id number %d has been deleted successfully%n", deleteDiary.getAuthor(), deleteDiary.getId());

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }

    public  String findAllEntries(String username){
        try{
            diaryServices.findAllEntries(username);
            return String.format("Entries of %s%n " + "%s", username, diaryServices.findAllEntries(username));

        }
        catch (DiaryExceptions e){
            return e.getMessage();
        }
    }



}