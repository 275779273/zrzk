package com.zrzk.rms.config;

import com.zrzk.rms.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //不加这个注解不生效，具体不详
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        // anon : 放行
        map.put("/login.html","anon");
        map.put("/user/login","anon");
        map.put("/static/**","anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/ui/**", "anon");
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "authc");
        //map.put("/**","anon");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //凭证匹配器 (密码加密)
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密的方式
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //设置加密的次数
        hashedCredentialsMatcher.setHashIterations(1);
        //设置是否加盐
        //hashedCredentialsMatcher.setHashSalted(true);
        return hashedCredentialsMatcher;
    }


    /*public static void main(String[] args) {
        //加密的方式
        String hashAlgorithmName = "MD5";
        //要加密的密码
        Object credentials = "123456";
        //是否加盐
        Object salt = ByteSource.Util.bytes("xiaoming");
        //加密的次数
        int hashIterations = 1;

        //真正用来加密的类
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
        System.out.println("simpleHash = " + simpleHash);

        //e10adc3949ba59abbe56e057f20f883e  : 1次
        //fc1709d0a95a6be30bc5926fdb7f22f4  : 1024次
        //48e231e66ff8943db0f6d2b6cb6536d2  : 加盐
    }
*/
}