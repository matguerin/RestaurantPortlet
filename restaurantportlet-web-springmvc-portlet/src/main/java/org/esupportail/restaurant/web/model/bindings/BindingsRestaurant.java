
package org.esupportail.restaurant.web.model.bindings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "areas",
    "dining-halls",
    "meals"
})
public class BindingsRestaurant {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("areas")
    private List<Area> areas = new ArrayList<Area>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("dining-halls")
    private List<Dining_hall> dining_halls = new ArrayList<Dining_hall>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("meals")
    private List<Meal> meals = new ArrayList<Meal>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("areas")
    public List<Area> getAreas() {
        return areas;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("areas")
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("dining-halls")
    public List<Dining_hall> getDining_halls() {
        return dining_halls;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("dining-halls")
    public void setDining_halls(List<Dining_hall> dining_halls) {
        this.dining_halls = dining_halls;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("meals")
    public List<Meal> getMeals() {
        return meals;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("meals")
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}