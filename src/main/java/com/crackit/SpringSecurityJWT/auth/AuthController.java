package com.crackit.SpringSecurityJWT.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController, bu sınıfın bir Spring MVC denetleyicisi olduğunu belirtir.
// Bu, HTTP isteklerini işlemek için kullanılır.
@RestController
@CrossOrigin
// @RequestMapping, bu denetleyicinin tüm yollarının "/crackit/v1/auth" ile başlaması gerektiğini belirtir.
@RequestMapping("/crackit/v1/auth")

// @RequiredArgsConstructor, Lombok tarafından otomatik olarak bir yapıcı oluşturur ve
// bu yapıcı, sınıftaki final alanları başlatmak için kullanılır.
@RequiredArgsConstructor
public class AuthController {

    // authService, kimlik doğrulama işlemlerini gerçekleştiren servis sınıfıdır.
    private final AuthService authService;

    // Bu metot, "/register" yoluna yapılan HTTP POST isteklerini işler.
    @PostMapping("/register")
    // register metodu, RegisterRequest nesnesini HTTP isteğinden alır ve AuthenticationResponse döner.
    public ResponseEntity<AuthenticationResponse> register(
            // @RequestBody, HTTP isteğinin gövdesindeki JSON verisini RegisterRequest nesnesine dönüştürür.
            @RequestBody RegisterRequest registerRequest
    ) {
        // authService.register metodu, kullanıcı kaydını gerçekleştirir ve bir AuthenticationResponse döner.
        AuthenticationResponse authResponse = authService.register(registerRequest);

        // ResponseEntity.ok, HTTP 200 OK yanıtını oluşturur ve authResponse'u yanıt gövdesine ekler.
        return ResponseEntity.ok(authResponse);
    }

    // Bu metot, "/authenticate" yoluna yapılan HTTP POST isteklerini işler.
    @PostMapping("/authenticate")
    // authenticate metodu, AuthenticationRequest nesnesini HTTP isteğinden alır ve AuthenticationResponse döner.
    public ResponseEntity<AuthenticationResponse> authenticate(
            // @RequestBody, HTTP isteğinin gövdesindeki JSON verisini AuthenticationRequest nesnesine dönüştürür.
            @RequestBody AuthenticationRequest request
    ) {
        // authService.authenticate metodu, kullanıcı kimlik doğrulamasını gerçekleştirir ve bir AuthenticationResponse döner.
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
