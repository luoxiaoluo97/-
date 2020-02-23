package com.boki.bokiclient.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @time: 2020/2/17
 * @author: LJF
 * @description:
 */
@Configuration
public class ShiroConfig {

    /**
     * 过滤器
     * @param SecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager SecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(SecurityManager);
        /**
         * anon     开放
         * authc    认证后访问
         * user     使用rememberMe之后可以直接访问
         * perms    获得资源权限后访问
         * role     获得角色权限后访问
         */
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/","anon");
        map.put("/p/*","anon");
        map.put("/user/login","anon");
        map.put("/user/register","anon");
        map.put("/user/sendCheckCode/*","anon");
        map.put("/user/modifyPwd","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        shiroFilterFactoryBean.setLoginUrl("/user/login");
//        shiroFilterFactoryBean.setUnauthorizedUrl("");

        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     * @param shiroRealm
     * @return
     */
    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm)
    {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm); //设置realm

        return manager;
    }

    @Bean(name = "shiroRealm")
    public ShiroRealm getShiroRealm(){
        return new ShiroRealm();
    }

    /**
     * 用于在thymeleaf使用shiro标签
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
