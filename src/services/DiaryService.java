package services;

import dtos.RegisterRequest;

public interface DiaryService {
    void register(RegisterRequest register);

    void lock (RegisterRequest register);

    boolean isLocked();
    void remove(RegisterRequest request);
    void unlock(RegisterRequest request);

}
