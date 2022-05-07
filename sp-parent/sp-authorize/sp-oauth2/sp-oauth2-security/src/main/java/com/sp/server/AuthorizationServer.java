package com.sp.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author eric
 * @date 2022/3/21 13:10
 **/
@Configuration(proxyBeanMethods = false)
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;
    @Autowired
    ClientDetailsService clientDetailsService;
    //认证管理
    @Autowired
    AuthenticationManager authenticationManager;
    //授权码服务
    @Autowired
    AuthorizationCodeServices authorizationCodeServices;
    /**
     * 配置令牌存储策略
     * */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
    /**
     * 令牌管理
     * */
    @Bean
    public AuthorizationServerTokenServices tokenService(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);//客户端信息服务
        services.setSupportRefreshToken(true);//是否支持刷新令牌
        services.setTokenStore(tokenStore);//令牌存储策略
        services.setAccessTokenValiditySeconds(7200);//令牌有效期2小时
        services.setRefreshTokenValiditySeconds(259200); //刷新令牌有效期3天
        return services;
    }

    /**
     * 配置客户端详细信息
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                //客户端ID
                .withClient("client_order_id")
                //客户端密钥
                .secret(new BCryptPasswordEncoder().encode("123456"))
                //可以访问的资源列表
                .resourceIds("resource1","resource2")
                //OAtuh2.0协议 支持5种授权类型
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                //允许授权范围
                .scopes("all")
                .autoApprove(false) // 是否需要跳转到授权页面，false需要跳转
                .redirectUris("https://www.baidu.com");

    }

    /**
     * 配置令牌(token)访问端点和令牌服务(token services)
     * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)//密码模式需要
                .authorizationCodeServices(authorizationCodeServices)//授权码模式需要
                .tokenServices(tokenService())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); //允许post提交方式
    }

    /**
     *配置令牌访问端点的安全约束
     * */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")  // /oauth/token
                .checkTokenAccess("permitAll()") // /oauth/check_token
                .allowFormAuthenticationForClients();
    }

}
