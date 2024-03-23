package dearDiary.controllers.data.repositories;

import dearDiary.controllers.data.models.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImp implements DiaryRepository{
    private List<Diary> diaries = new ArrayList<>();
    private int countId;

    @Override
    public Diary save(Diary diary) {
        updateCheck(diary);
        diaries.add(diary);
        return diary;
    }

    private void updateCheck(Diary diary) {
        if(diary.getId() == 0){
            diary.setId(++countId);
        }
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public Diary findById(String username) {
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username)) {
                return diary;
            }
        }

        return null;
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public void delete(String username) {
        Diary diary = findById(username);
        diaries.remove(diary);
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
    }
}