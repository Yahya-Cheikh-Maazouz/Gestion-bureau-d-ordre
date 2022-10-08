package com.maazouz.servicegestioncourrier.EndPoint;

import com.maazouz.servicegestioncourrier.Model.pojo.Employer;
import com.maazouz.servicegestioncourrier.MultiTenant.Interceptor.TenantContext;
import com.maazouz.servicegestioncourrier.Remote.IUserRemote;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Transactional
@CrossOrigin
public class userController {

    private final IUserRemote api;

    @PostMapping( path ="/test/user/{id}")
    public void findUser(@PathVariable Long id)
    {

        HttpHeaders headers = new HttpHeaders();

        headers.add("XTenantID", TenantContext.getCurrentTenant());

        Employer Employer = api.findEmployer(headers,id);
        System.out.println(" begin transaction ***************");
        System.out.println( Employer.toString());
        System.out.println(" end transaction   ***************");
    }



}
