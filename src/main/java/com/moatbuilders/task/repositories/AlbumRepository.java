package com.moatbuilders.task.repositories;

import com.moatbuilders.task.domian.album.AlbumDTO;
import com.moatbuilders.task.domian.album.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<AlbumEntity, String> {
    Optional<AlbumEntity> findByAlbumName(String albumName);
}