package com.guliz.bookstore.customer.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 9192004477913969233L;

    @Valid
    @NotNull
    @JsonProperty("messageText")
    private String messageText;

    @Valid
    @NotNull
    @JsonProperty("returnCode")
    private HttpStatus returnCode;

}
