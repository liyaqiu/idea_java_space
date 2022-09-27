package com.gzzn.service.gateway.security2.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author eric
 * @date 2022/9/27 18:20
 **/
@Component
@Slf4j
public class MyContextRepository implements ServerSecurityContextRepository {
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        //log.debug("MyContextRepository save");
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        log.debug("MyContextRepository load");
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken("", "");
        Mono<SecurityContext> mono = new MyAuthenticationManager().authenticate(newAuthentication).map(SecurityContextImpl::new);
        return mono;
    }
}

class MyAuthenticationManager implements  ReactiveAuthenticationManager{

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.fromCallable(() -> {
                   /* List<String> roles = jwtTool.getUserRoles(token);
                    List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());*/
                    /*UserEntity principal = new UserEntity();
                    principal.setUsername(username);*/
            return new UsernamePasswordAuthenticationToken(null, null, null);
        });
    }
}