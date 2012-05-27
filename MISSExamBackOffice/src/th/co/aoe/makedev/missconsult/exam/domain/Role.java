// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:11:38 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Role.java

package th.co.aoe.makedev.missconsult.exam.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


// Referenced classes of package th.co.aoe.makedev.missconsult.exam.domain:
//            User

@Entity(name="role")
public class Role
{

    public Role()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Integer getRole()
    {
        return role;
    }

    public void setRole(Integer role)
    {
        this.role = role;
    }

    @Id
    private Long id;
    @OneToOne
    private User user;
    private Integer role;
}
