package com.geveit.melod.song;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<SongOutput> output = songService.getAll();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        SongOutput output = songService.getById(id);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SongInput input) {
        SongOutput output = songService.create(input);
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody SongInput input) {
        SongOutput output = songService.update(id, input);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        songService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
