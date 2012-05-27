// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:14:33 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CustomUserDetailsService.java

package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.exam.service.UserRepository;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService
    implements UserDetailsService
{

    public CustomUserDetailsService()
    {
    }

    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException
    {
        try
        {
            th.co.aoe.makedev.missconsult.exam.domain.User domainUser = userRepository.findByUsername(username);
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            return new User(domainUser.getUsername(), domainUser.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(domainUser.getRole().getRole()));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Collection getAuthorities(Integer role)
    {
        List authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List getRoles(Integer role)
    {
        List roles = new ArrayList();
        if(role.intValue() == 1)
        {
            roles.add("ROLE_USER");
            roles.add("ROLE_ADMIN");
        } else
        if(role.intValue() == 2)
            roles.add("ROLE_USER");
        return roles;
    }

    public static List getGrantedAuthorities(List roles)
    {
        List authorities = new ArrayList();
        String role;
        for(Iterator iterator = roles.iterator(); iterator.hasNext(); authorities.add(new SimpleGrantedAuthority(role)))
            role = (String)iterator.next();

        return authorities;
    }

    @Autowired
    private UserRepository userRepository;
}
