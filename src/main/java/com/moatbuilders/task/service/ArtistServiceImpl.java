package com.moatbuilders.task.service;

import com.moatbuilders.task.domian.artist.ArtistDTO;

import java.util.List;

public interface ArtistServiceImpl {
    List<List<ArtistDTO>> getlistArtistsSync() throws Exception;

    List<ArtistDTO> getArtistsSync(String artistId) throws Exception;
}
