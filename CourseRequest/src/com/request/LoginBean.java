package com.request;

import com.admin.BuildSQL;

public class LoginBean {
	 	private String name;
	 	private String password;

	 	public String buildsql(){
	 		BuildSQL.build("8889","root","root");
	 		return "success";
	 	}
	    public String getName ()
	    {
	        return name;
	    }


	    public void setName (final String name)
	    {
	        this.name = name;
	    }


	    public String getPassword ()
	    {
	        return password;
	    }


	    public void setPassword (final String password)
	    {
	        this.password = password;
	    }
}
