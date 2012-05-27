// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:14:26 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UserRepository.java

package th.co.aoe.makedev.missconsult.exam.service;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.aoe.makedev.missconsult.exam.domain.User;

public interface UserRepository
    extends JpaRepository
{

    public abstract User findByUsername(String s);
}
