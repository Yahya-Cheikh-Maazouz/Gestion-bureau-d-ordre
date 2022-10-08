package com.maazouz.servicegestioncourrier.Remote;

import com.maazouz.servicegestioncourrier.Model.pojo.Employer;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service",url = "localhost:5050/api/v1/registration")
public interface IUserRemote {


    @GetMapping("/user/{id}")
    public Employer findEmployer(@RequestHeader HttpHeaders headers, @PathVariable long id);

}
