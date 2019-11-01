package com.zrzk.rms.config;

import com.zrzk.rms.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //不加这个注解不生效
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

        //customRealm.setCachingEnabled(true);
        ////启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        //customRealm.setAuthenticationCachingEnabled(true);
        ////缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        //customRealm.setAuthenticationCacheName("authenticationCache");
        ////启用授权缓存，即缓存AuthorizationInfo信息，默认false
        //customRealm.setAuthorizationCachingEnabled(true);
        ////缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        //customRealm.setAuthorizationCacheName("authorizationCache");
        return customRealm;
    }

    /**
     * shiro缓存管理器;
     * 需要添加到securityManager中
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //配置记住我
        securityManager.setRememberMeManager(rememberMeManager());

        //配置 ehcache缓存管理器
        //securityManager.setCacheManager(ehCacheManager());

        //配置自定义session管理，使用redis
        //securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> map = new LinkedHashMap<>();
        // anon : 放行

        //map.put("/index.html","anon");
        map.put("/static/**", "anon");
        map.put("/user/login", "anon");
        map.put("/user/logout", "anon");
        map.put("/index.html", "anon");
        map.put("/login.html", "anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/img/**", "anon");
        map.put("/media/**", "anon");
        //authc 拦截 , anon 放行
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        //map.put("/**", "authc");
        map.put("/**","anon");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/index.html");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error.html");
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
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密的方式
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //设置加密的次数
        hashedCredentialsMatcher.setHashIterations(1);
        //设置是否加盐
        //hashedCredentialsMatcher.setHashSalted(true);
        return hashedCredentialsMatcher;
    }

    /**
     * 让某个实例的某个方法的返回值注入为Bean的实例
     * Spring静态注入
     *
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
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
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
        System.out.println("simpleHash = " + simpleHash.toString());

        //e10adc3949ba59abbe56e057f20f883e  : 1次
        //fc1709d0a95a6be30bc5926fdb7f22f4  : 1024次
        //48e231e66ff8943db0f6d2b6cb6536d2  : 加盐
    }*/
}