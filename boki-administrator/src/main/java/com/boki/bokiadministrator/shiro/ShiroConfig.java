//package com.boki.bokiadministrator.shiro;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import com.boki.bokiapi.entity.dto.dbsource.RolePermissionDTO;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @time: 2020/2/17
// * @author: LJF
// * @description:
// */
//@Configuration
//public class ShiroConfig {
//
//
//    private static final String ANON = "anon";
//    private static final String AUTHC = "authc";
//    private static final String USER = "user";
//    private static final String PERMS = "perms";
//    private static final String ROLE = "role";
//    private static final String LOGOUT = "logout";
//
//
//    @Autowired
//    private ArrayList<RolePermissionDTO> permission;
//
//    /**
//     * 过滤器
//     * @param SecurityManager
//     * @return
//     */
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager SecurityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(SecurityManager);
//        /**
//         * anon     开放
//         * authc    认证后访问
//         * user     使用rememberMe之后可以直接访问
//         * perms    获得资源权限后访问,需要放在拦截所有的前面，否者失效
//         * role     获得角色权限后访问,需要放在拦截所有的前面，否者失效
//         */
//        Map<String, String> map = new LinkedHashMap<>();
//        //静态资源
//        map.put("/images/**",ANON);
//        map.put("/static/**",ANON);
//        //首页
//        map.put("/",ANON);
//        //用户操作
//        map.put("/login",ANON);
//        map.put("/login/exit",LOGOUT);
//        //授权访问
//        map.put("/user/role/change",PERMS+"[站长]");
//        map.put("/**",AUTHC);
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//
//        shiroFilterFactoryBean.setLoginUrl("/user/login");
////        shiroFilterFactoryBean.setUnauthorizedUrl("");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 安全管理器
//     * @param shiroRealm
//     * @return
//     */
//    @Bean(name = "manager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm)
//    {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(shiroRealm); //设置realm
//
//        return manager;
//    }
//
//    @Bean(name = "shiroRealm")
//    public ShiroRealm getShiroRealm(){
//        return new ShiroRealm();
//    }
//
//    /**
//     * 用于在thymeleaf使用shiro标签
//     * @return
//     */
//    @Bean(name = "shiroDialect")
//    public ShiroDialect getShiroDialect(){
//        return new ShiroDialect();
//    }
//}
