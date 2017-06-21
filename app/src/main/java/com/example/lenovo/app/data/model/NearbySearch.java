package com.example.lenovo.app.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 20.06.2017.
 */

public class NearbySearch {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "html_attributions",
            "next_page_token",
            "results",
            "status"
    })
    @JsonProperty("html_attributions")
    public List<Object> htmlAttributions = null;
    @JsonProperty("next_page_token")
    public String nextPageToken;
    @JsonProperty("results")
    public List<NearbySearchDto> results = null;
    @JsonProperty("status")
    public String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public NearbySearch() {
    }

    public NearbySearch(List<Object> htmlAttributions, String nextPageToken, List<NearbySearchDto> results, String status, Map<String, Object> additionalProperties) {
        this.htmlAttributions = htmlAttributions;
        this.nextPageToken = nextPageToken;
        this.results = results;
        this.status = status;
        this.additionalProperties = additionalProperties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<NearbySearchDto> getResults() {
        return results;
    }

    public void setResults(List<NearbySearchDto> results) {
        this.results = results;
    }
}
