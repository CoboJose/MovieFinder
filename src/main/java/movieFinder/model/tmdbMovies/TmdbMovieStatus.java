
package movieFinder.model.tmdbMovies;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "favorite",
    "rated",
    "watchlist"
})
public class TmdbMovieStatus {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("favorite")
    private Boolean favorite;
    @JsonProperty("rated")
    private Rated rated;
    @JsonProperty("watchlist")
    private Boolean watchlist;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("favorite")
    public Boolean getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("rated")
    public Rated getRated() {
        return rated;
    }

    @JsonProperty("rated")
    public void setRated(Rated rated) {
        this.rated = rated;
    }

    @JsonProperty("watchlist")
    public Boolean getWatchlist() {
        return watchlist;
    }

    @JsonProperty("watchlist")
    public void setWatchlist(Boolean watchlist) {
        this.watchlist = watchlist;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
