package com.example.lenovo.app.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 19.06.2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "geometry",
        "icon",
        "id",
        "name",
        "place_id",
        "reference",
        "scope",
        "types",
        "vicinity",
        "rating",
        "opening_hours",
        "photos"
})

public class NearbySearchDto implements Serializable {

    @JsonProperty("geometry")
    public Geometry geometry;
    @JsonProperty("icon")
    public String icon;
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("place_id")
    public String placeId;
    @JsonProperty("reference")
    public String reference;
    @JsonProperty("scope")
    public String scope;
    @JsonProperty("types")
    public List<String> types = null;
    @JsonProperty("vicinity")
    public String vicinity;
    @JsonProperty("rating")
    public Integer rating;
    @JsonProperty("opening_hours")
    public OpeningHours openingHours;
    @JsonProperty("photos")
    public List<Photo> photos = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public NearbySearchDto() {
    }

    public NearbySearchDto(Geometry geometry, String icon, String id, String name, String placeId, String reference, String scope, List<String> types, String vicinity, Integer rating, OpeningHours openingHours, List<Photo> photos) {
        this.geometry = geometry;
        this.icon = icon;
        this.id = id;
        this.name = name;
        this.placeId = placeId;
        this.reference = reference;
        this.scope = scope;
        this.types = types;
        this.vicinity = vicinity;
        this.rating = rating;
        this.openingHours = openingHours;
        this.photos = photos;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
