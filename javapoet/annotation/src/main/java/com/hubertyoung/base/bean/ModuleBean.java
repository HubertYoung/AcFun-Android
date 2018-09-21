package com.hubertyoung.base.bean;

import java.util.ArrayList;
import java.util.List;

public class ModuleBean {
	private String name;

	private String alias;

	private List< EnvironmentBean > environments;

	public ModuleBean() {
		this( "", "" );
	}

	public ModuleBean( String name, String alias ) {
		this( name, alias, new ArrayList< EnvironmentBean >() );
	}

	public ModuleBean( String name, String alias, List< EnvironmentBean > environments ) {
		this.name = name;
		this.alias = alias;
		this.environments = environments;
	}

	public List< EnvironmentBean > getEnvironments() {
		return environments;
	}

	public String getAlias() {
		return alias;
	}

	public String getName() {
		return name;
	}
}