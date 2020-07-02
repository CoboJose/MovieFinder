package movieFinder.model.resource;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import movieFinder.model.youtubeplaylist.YouTubePlaylist;
import movieFinder.model.youtubevideo.Item;
import movieFinder.model.youtubevideo.Youtube;

public class YouTubeResource {
	private static final Logger log = Logger.getLogger(YouTubeResource.class.getName());

    private final String uri = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=";
    private final String api = "AIzaSyBn9fs0Tw12N85aXbCIeTI4nnCKdJ14ZNo";

    public YouTubeResource() {  
    }

    public Item getVideos(String query) {
        ClientResource cr = null;
        Youtube videos = null;
        try {
            cr = new ClientResource(uri + query + "+castellano&key=" + api);
            videos = cr.get(Youtube.class);

        } catch (ResourceException re) {
            log.warning("Youtube Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return videos.getItems().get(0);

    }
    
    public movieFinder.model.youtubeplaylist.Item getPlaylist(String query) {
    	ClientResource cr = null;
        YouTubePlaylist playlist = null;
        try {
            cr = new ClientResource(uri + query + "+original+soundtrack&key=" + api + "&type=playlist");
            playlist = cr.get(YouTubePlaylist.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return playlist.getItems().get(0);
    }

}
