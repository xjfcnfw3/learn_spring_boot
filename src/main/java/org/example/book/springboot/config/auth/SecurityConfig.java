package org.example.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.book.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //h2-console 화면을 사용하기 위한 설정
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                // url 별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()// 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // USER 권한
                .anyRequest().authenticated() // 나머지 Url에 대하여 인증된 사용자만 가능하도록 한다.
                .and()
                // 로그아웃 기능에 대한 설정
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()// oauth2 로그인 기능에 대한 설정
                .userInfoEndpoint()// oauth2 로그인 성공 이후 사용자 정보를 가져올 때 설정
                //리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
                .userService(customOAuth2UserService);
    }
}
