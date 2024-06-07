package com.crackit.SpringSecurityJWT.secured;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController, bu sınıfın bir Spring MVC denetleyicisi olduğunu belirtir.
// Bu, HTTP isteklerini işlemek için kullanılır.
@RestController

// @RequestMapping, bu denetleyicinin tüm yollarının "/crackit/v1/admin" ile başlaması gerektiğini belirtir.
@RequestMapping("/crackit/v1/admin")

// @PreAuthorize, bu denetleyicinin tüm metodlarının sadece 'ADMIN' rolüne sahip kullanıcılar tarafından erişilebilir olduğunu belirtir.
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    // Bu metod, 'admin:read' yetkisine sahip kullanıcılar tarafından erişilebilir.
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAdmin() {
        return "Secured Endpoint :: GET - Admin controller";
    }

    // Bu metod, 'admin:create' yetkisine sahip kullanıcılar tarafından erişilebilir.
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "Secured Endpoint :: POST - Admin controller";
    }
}
