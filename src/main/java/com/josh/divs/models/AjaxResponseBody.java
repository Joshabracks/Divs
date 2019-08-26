package com.josh.divs.models;

import java.util.List;

public class AjaxResponseBody {

	String msg;
    List<Div> result;
    
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Div> getResult() {
		return result;
	}
	public void setResult(List<Div> result) {
		this.result = result;
	}
    
    
}
