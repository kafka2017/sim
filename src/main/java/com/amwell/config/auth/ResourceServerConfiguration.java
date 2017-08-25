//package com.amwell.config.auth;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//import com.amwell.common.AuthResource;
//
//
///**
// * auth 资源服务器
// * @author 番茄很忙
// *
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//	
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources){
//		resources.resourceId(AuthResource.RESOURCEID).stateless(true);
//	}
//	
//	@Override
//	public void configure(HttpSecurity http)throws Exception{
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//			.and().requestMatchers().anyRequest()
//			.and().anonymous()
//			.and().authorizeRequests()
//		.antMatchers("/simbase/**").authenticated();//配置security访问控制，必须认证过后才可以访问
//	}
//
//}
