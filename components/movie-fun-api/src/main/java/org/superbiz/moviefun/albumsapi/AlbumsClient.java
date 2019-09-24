package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import org.superbiz.moviefun.moviesapi.MovieInfo;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {
    private String albumUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumUrl, RestOperations restOperations) {
        this.albumUrl = albumUrl;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumUrl, album, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumUrl + "/{id}", AlbumInfo.class, id);
    }


    public void deleteAlbum(AlbumInfo album) {
        restOperations.delete(albumUrl + "/" + album.getId());
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumUrl, GET, null, albumListType).getBody();
    }
}
