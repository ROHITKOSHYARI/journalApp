package com.Rohit.journalapp.api.response;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WetherResponse{

    private Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("temperature")
        private int Temperature;

        @JsonProperty("weather_code")
        private int weatherCode;

        @JsonProperty("feelslike")
        private int feelsLike;

    }

}


