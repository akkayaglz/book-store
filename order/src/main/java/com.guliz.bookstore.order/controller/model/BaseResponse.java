package com.guliz.bookstore.order.controller.model;

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

    private static final long serialVersionUID = 8967972037833185777L;

    @NotNull
    @JsonProperty("message")
    private String message;

}
