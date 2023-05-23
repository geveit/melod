package com.geveit.melod.song;

import java.util.List;

public interface SongService {
    List<SongOutput> getAll();
    SongOutput getById(long id);
    SongOutput create(SongInput input);
    SongOutput update(long id, SongInput input);
    void delete(long id);
}
