package com.moatbuilders.task.controller;

import com.moatbuilders.task.domian.artist.ArtistDTO;
import com.moatbuilders.task.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/artist")
public class ArtistController {


    @Autowired
    ArtistService artistService;


    @GetMapping("/{artist_id}")
    ResponseEntity<List<ArtistDTO>> findById(@PathVariable("artist_id") String artistId) {

        List<ArtistDTO> listArtist = new ArrayList<>();

        try {
            listArtist = artistService.getArtistsSync(artistId);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listArtist);

    }

    @GetMapping
    ResponseEntity<Object> findAll() {

        List<ArtistDTO> listArtist = new ArrayList<>();

        try {
            var result = artistService.getlistArtistsSync();
            listArtist = extractObjects(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listArtist);
    }

    public static List<ArtistDTO> extractObjects(List<List<ArtistDTO>> artistsLists) {
        List<ArtistDTO> result = new ArrayList<>();

        for (List<ArtistDTO> artists : artistsLists) {
            if (!artists.isEmpty()) {
                result.add(artists.get(0));
            }
        }

        return result;
    }

}
