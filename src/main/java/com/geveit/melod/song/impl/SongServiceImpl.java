package com.geveit.melod.song.impl;

import com.geveit.melod.exception.EntityNotFoundException;
import com.geveit.melod.song.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl  implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<SongOutput> getAll() {
        return songRepository.findAll().stream().map(e -> new SongOutput(e.getId(), e.getWorkingTitle())).toList();
    }

    @Override
    public SongOutput getById(long id) {
        return songRepository.findById(id)
                .map(e -> new SongOutput(e.getId(), e.getWorkingTitle()))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public SongOutput create(SongInput input) {
        Song song = new Song();
        song.setWorkingTitle(input.getWorkingTitle());
        Song newSong = songRepository.save(song);
        return new SongOutput(newSong.getId(), newSong.getWorkingTitle());
    }

    @Override
    public SongOutput update(long id, SongInput input) {
        Song song = songRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        song.setWorkingTitle(input.getWorkingTitle());

        Song updatedSong = songRepository.save(song);
        return new SongOutput(updatedSong.getId(), updatedSong.getWorkingTitle());
    }

    @Override
    public void delete(long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        songRepository.delete(song);
    }
}
