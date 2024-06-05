package com.moatbuilders.task.controller;

import com.moatbuilders.task.domian.album.AlbumDTO;
import com.moatbuilders.task.domian.album.AlbumEntity;
import com.moatbuilders.task.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/album")
public class AlbumController {


    @Autowired
    private AlbumService albumService;


    @PostMapping
    ResponseEntity create(@RequestBody @Valid AlbumDTO albumsDto) {

        var albumNametOptional = albumService.findByAlbumName((albumsDto.albumName()));
        if (albumNametOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the album name already exists.");
        }

        var album = new AlbumEntity(albumsDto.artist(), albumsDto.albumName(), albumsDto.year());

        albumService.create(album);

        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }


    @PutMapping
    public ResponseEntity<String> update(@RequestBody @Valid AlbumDTO albumsDto) {
        var albumNametOptional = albumService.findByAlbumName((albumsDto.albumName()));

        if (albumNametOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }

        var album = new AlbumEntity(albumNametOptional.get().getId(), albumsDto.artist(), albumsDto.albumName(), albumsDto.year());

        albumService.create(album);

        return ResponseEntity.status(HttpStatus.OK).body("UPDATE");
    }


    @GetMapping("/{album-name}")
    ResponseEntity findById(@PathVariable("album-name") String albumName) {

        var albumNametOptional = albumService.findByAlbumName((albumName));
        if (albumNametOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }

        return ResponseEntity.status(HttpStatus.OK).body(albumNametOptional);
    }

    @GetMapping
    ResponseEntity findAll() {

        var list = albumService.findAll();

        if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("[]");

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable("id") String id) {

        var albumNametOptional = albumService.findByAlbumName((id));
        if (albumNametOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }

        albumService.deleteById(albumNametOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body("DELETE");
    }


}
