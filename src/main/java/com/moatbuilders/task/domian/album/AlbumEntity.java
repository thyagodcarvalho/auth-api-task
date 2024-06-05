package com.moatbuilders.task.domian.album;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "album", schema = "public")
@Entity(name = "albumEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String artist;
    @Column(name = "album_name")
    private String albumName;
    private String year;

    public AlbumEntity(String artist, String albumName, String year) {
        this.artist = artist;
        this.albumName = albumName;
        this.year = year;
    }
}
