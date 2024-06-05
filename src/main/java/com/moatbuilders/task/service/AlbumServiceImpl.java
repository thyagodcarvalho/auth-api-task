package com.moatbuilders.task.service;

import com.moatbuilders.task.domian.album.AlbumEntity;

import java.util.List;
import java.util.Optional;

public interface AlbumServiceImpl {
    AlbumEntity create(AlbumEntity albumEntity);

    List<AlbumEntity> findAll();

    Optional<AlbumEntity> findById(String id);

    Optional<AlbumEntity> findByAlbumName(String albumName);

    void deleteById(String id);
}
