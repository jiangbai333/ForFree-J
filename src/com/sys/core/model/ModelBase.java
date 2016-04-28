package com.sys.core.model;

import com.sys.core.controller.ControllerBase;

public class ModelBase {
	
	protected ControllerBase cb = null;
	
	public ModelBase(ControllerBase cb) {
		this.cb = cb;
	}
}