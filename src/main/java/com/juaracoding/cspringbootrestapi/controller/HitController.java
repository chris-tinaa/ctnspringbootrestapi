package com.juaracoding.cspringbootrestapi.controller;

import com.juaracoding.cspringbootrestapi.util.ExecuteSMTP;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hit")
public class HitController {


    @GetMapping("/v1/smtpz")
    public String hitSMTP()
    {
        ExecuteSMTP executeSMTP = new ExecuteSMTP();
        String [] strArr = {"chrislinlin.cl@gmail.com","chrislinlinn@gmail.com","chrislin.linn@gmail.com"};
        String [] strArr1 = {"Verifikasi1","Verifikasi2","Verifikasi3"};

        executeSMTP.sendSMTPToken(strArr,"TEST123",strArr1,"\\data\\ver_regis.html");

        return "OK";
    }
}
