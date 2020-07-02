
package movieFinder.model.tmdbWatchlist;

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
    "media_type",
    "media_id",
    "watchlist"
})
public class TmdbAddWatchlist {

    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("media_id")
    private Integer mediaId;
    @JsonProperty("watchlist")
    private Boolean watchlist;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("media_type")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("media_type")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("media_id")
    public Integer getMediaId() {
        return mediaId;
    }

    @JsonProperty("media_id")
    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
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
