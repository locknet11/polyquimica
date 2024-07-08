package com.polyquimica.app.domain.shared;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseList<T> {
    @JsonProperty("content")
    private List<?> content;

    @JsonProperty("totalPages")
    private Integer totalPages;

    @JsonProperty("totalElements")
    private Long totalElements;

    @JsonProperty("page")
    private int page;
    
    @JsonProperty("size")
    private int size;
}
