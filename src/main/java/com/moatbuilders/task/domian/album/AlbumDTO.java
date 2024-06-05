package com.moatbuilders.task.domian.album;

import jakarta.validation.constraints.NotBlank;

public record AlbumDTO(
        @NotBlank String artist,
        @NotBlank String albumName,
        @NotBlank String year
) {}
