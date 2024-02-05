package com.address.service;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8081", value = "User-Client")
@FeignClient(name = "USER-SERVICE:8081")
public interface IUserClient {

    @GetMapping(
            value = "/user/profile/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    String getUser(
            @Parameter(description = "User Identifier", required = true) @PathVariable int id
    );

}
