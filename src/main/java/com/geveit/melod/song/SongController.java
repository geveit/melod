package com.geveit.melod.song;

import com.geveit.melod.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<SongOutput> output =  songRepository.findAll().stream().map(e -> new SongOutput(e.getId(), e.getWorkingTitle())).toList();

        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        SongOutput output = songRepository.findById(id)
                .map(e -> new SongOutput(e.getId(), e.getWorkingTitle()))
                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SongInput input) {
        Song song = new Song();
        song.setWorkingTitle(input.getWorkingTitle());
        Song newSong = songRepository.save(song);
        SongOutput output = new SongOutput(newSong.getId(), newSong.getWorkingTitle());
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody SongInput input) {
        Song song = songRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        song.setWorkingTitle(input.getWorkingTitle());

        Song updatedSong = songRepository.save(song);
        SongOutput output = new SongOutput(updatedSong.getId(), updatedSong.getWorkingTitle());

        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        songRepository.delete(song);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
