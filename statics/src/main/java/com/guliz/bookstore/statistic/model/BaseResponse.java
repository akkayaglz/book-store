package com.guliz.bookstore.statistic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 9192004477913969233L;

    @NotNull
    @JsonProperty("message")
    private String message;

}
