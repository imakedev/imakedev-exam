package th.co.aoe.makedev.missconsult.utils;

import java.sql.Connection;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class CommonDataSource {
	@Resource(name = "jdbc/missdb")
    private static DataSource ds;
	private static Connection con ; 
	public static org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs ;
	public static final ResourceBundle bundle;
	public static String SCHEMA="";
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		SCHEMA=bundle.getString("schema");
	}
	public static Connection getConnection(){
	/*
	org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;*/
	try {
		basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
		con = basicDs.getConnection();//("oracle", "password");//Connection();
	}catch(Exception ex){
		ex.printStackTrace();
	
	}
		return con;
	}
}
