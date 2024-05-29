package com.moatbuilders.task.controller;

import com.moatbuilders.task.domian.album.AlbumDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/album")
public class AlbumController {

//    @Autowired
//    private UserRepository userRepository;


    @PostMapping
    ResponseEntity<String> create(@RequestBody @Valid AlbumDTO albumsDto) {
//        var save = userRepository.save(albumsDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }


    @PutMapping
    public ResponseEntity<String> update(@RequestBody @Valid AlbumDTO albumsDto) {

        return ResponseEntity.status(HttpStatus.OK).body("UPDATE");
    }


    @GetMapping("/{artist_id}")
    ResponseEntity<String> findById(@PathVariable("artist_id") String artistId) {

        return ResponseEntity.status(HttpStatus.OK).body("GET");
    }

    @GetMapping
    ResponseEntity<String> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body("GETALL");
    }


    @DeleteMapping("/{artist_id}")
    ResponseEntity<String> delete(@PathVariable("artist_id")  String artistId) {

        return ResponseEntity.status(HttpStatus.OK).body("DELETE");
    }

}
