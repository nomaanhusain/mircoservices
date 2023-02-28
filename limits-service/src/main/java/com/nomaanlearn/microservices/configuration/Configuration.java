package com.nomaanlearn.microservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//We want to pick values from configuration ie. application.properties or config server

@Component
@ConfigurationProperties("limits-service") //same as application.properties or config-server via git repo in git-localconfig-repo folder
public class Configuration {
	
	//properties names are same as from application.properties hence it will be picked up from there
	private int minimum;
	private int maximum;
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	

}
