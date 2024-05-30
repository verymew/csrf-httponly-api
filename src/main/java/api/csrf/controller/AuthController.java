package api.csrf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {
    @PostMapping("/post")
    public Mono<String> devolver(@RequestBody String nome){
        return Mono.just(nome);
    }
    @GetMapping("/ju")
    public Mono<String> teste(){
        return Mono.just("Oiii");
    }
    @GetMapping("/csrf")
    public Mono<CsrfToken> csrfToken(ServerWebExchange exchange) {
        Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());
        return csrfToken != null ? csrfToken : Mono.empty();
    }
}
