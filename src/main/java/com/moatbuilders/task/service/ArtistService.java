package com.moatbuilders.task.service;

import com.moatbuilders.task.domian.artist.ArtistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ArtistService implements ArtistServiceImpl {

    private static final String urlArtist = "https://europe-west1-madesimplegroup-151616.cloudfunctions.net/artists-api-controller";
    private static final String urlToken = "Basic ZGV2ZWxvcGVyOlpHVjJaV3h2Y0dWeQ==";

    @Override
    public List<List<ArtistDTO>> getlistArtistsSync() throws Exception {

        try {
            var restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", urlToken);

            HttpEntity<String> httpRequest = new HttpEntity<>(headers);

            ResponseEntity<Map<String, List<List<ArtistDTO>>>> response = restTemplate.exchange(
                    urlArtist,
                    HttpMethod.GET,
                    httpRequest,
                    new ParameterizedTypeReference<Map<String, List<List<ArtistDTO>>>>() {
                    }
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().get("json");
            } else {
                return Collections.emptyList();
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ArtistDTO> getArtistsSync(String artistId) throws Exception {

        try {
            var restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", urlToken);

            HttpEntity<String> httpRequest = new HttpEntity<>(headers);

            ResponseEntity<Map<String,List<ArtistDTO>>> response = restTemplate.exchange(
                    urlArtist + "?artist_id=" + artistId,
                    HttpMethod.GET,
                    httpRequest,
                    new ParameterizedTypeReference<Map<String, List<ArtistDTO>>>() {
                    }
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().get("json");
            } else {
                return Collections.emptyList();
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
