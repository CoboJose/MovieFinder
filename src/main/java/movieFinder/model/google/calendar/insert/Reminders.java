
package movieFinder.model.google.calendar.insert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "useDefault",
    "overrides"
})
public class Reminders {

    @JsonProperty("useDefault")
    private Boolean useDefault;
    @JsonProperty("overrides")
    private List<Override> overrides = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("useDefault")
    public Boolean getUseDefault() {
        return useDefault;
    }

    @JsonProperty("useDefault")
    public void setUseDefault(Boolean useDefault) {
        this.useDefault = useDefault;
    }

    @JsonProperty("overrides")
    public List<Override> getOverrides() {
        return overrides;
    }

    @JsonProperty("overrides")
    public void setOverrides(List<Override> overrides) {
        this.overrides = overrides;
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
