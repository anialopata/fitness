package com.anialopata.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ania on 2018-07-22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private String name;

    private String description;

    @JsonProperty("category_url")
    private String categoryUrl;
}
