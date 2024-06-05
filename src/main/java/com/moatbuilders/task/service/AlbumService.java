package com.moatbuilders.task.service;

import com.moatbuilders.task.domian.album.AlbumEntity;
import com.moatbuilders.task.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements AlbumServiceImpl {

    @Autowired
    private AlbumRepository albumRepository;


    @Override
    public AlbumEntity create(AlbumEntity albumEntity) {
        return albumRepository.save(albumEntity);
    }

    @Override
    public List<AlbumEntity> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<AlbumEntity> findById(String id) {
        return albumRepository.findById(id);
    }

    @Override
    public Optional<AlbumEntity> findByAlbumName(String albumName) {
        return albumRepository.findByAlbumName(albumName);
    }

    @Override
    public void deleteById(String id) {
        albumRepository.deleteById(id);
    }
}
