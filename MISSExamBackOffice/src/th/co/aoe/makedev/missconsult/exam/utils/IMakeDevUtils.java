// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:15:07 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IMakeDevUtils.java

package th.co.aoe.makedev.missconsult.exam.utils;


public class IMakeDevUtils
{

    public IMakeDevUtils()
    {
    }

    public static final int calculatePage(int perPage, int total)
    {
        return total % perPage != 0 ? total / perPage + 1 : total / perPage;
    }
}
