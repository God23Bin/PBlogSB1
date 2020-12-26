package com.bin23.blog.config;

import com.bin23.blog.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 该类是 Spring Security 的配置类
 * 三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Security 注解。
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码加密
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("index.html").permitAll()
                .antMatchers("/toRegisterPage").permitAll()
                .antMatchers("/userRegister").permitAll()
                .antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()//放行静态资源，CSS，JS什么的
                .anyRequest().authenticated()//表示除了上面的映射地址外，其他的都要登录的
                .and().formLogin();//formLogin配置了默认的Spring Security登录页面

        // 自定义用户登录页面控制
        http.formLogin().loginPage("/userLogin").permitAll()
                .usernameParameter("phone_number")//这个值要跟登陆页面form表单input的name属性的属性值一样
                .passwordParameter("password")//同样和页面保持一致
                .defaultSuccessUrl("/")
                .failureUrl("/userLogin?error");

        // 自定义用户退出
        http.logout().logoutUrl("/myLogout").logoutSuccessUrl("/");


        // 关闭CSRF跨域
        http.csrf().disable();

        // 解决Refused to display in a frame because it set 'X-Frame-Options' to 'DENY'的解决办法
        // Spring Boot Spring Securty 的 x-frame-options deny
        // 默认是deny，会让editor.md的图片上传显示不出路径，F12后才发现的，都是这个deny搞的鬼，改成sameOrigin
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }


}
