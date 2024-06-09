package com.crackit.SpringSecurityJWT.secured;

import org.springframework.web.bind.annotation.*;

// @RestController, bu sınıfın bir Spring MVC denetleyicisi olduğunu belirtir.
// Bu, HTTP isteklerini işlemek için kullanılır.
@RestController
@CrossOrigin
// @RequestMapping, bu denetleyicinin tüm yollarının "/crackit/v1/management" ile başlaması gerektiğini belirtir.
@RequestMapping("/crackit/v1/management")
public class MemberController {

    // Bu metod, HTTP GET isteklerini işler ve "Secured Endpoint :: GET - Member controller" mesajını döndürür.
    @GetMapping
    public String getMember() {
        return "Secured Endpoint :: GET - Member controller";
    }

    // Bu metod, HTTP POST isteklerini işler ve "POST:: management controller" mesajını döndürür.
    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
}
