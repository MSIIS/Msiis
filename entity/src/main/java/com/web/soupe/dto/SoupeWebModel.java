package com.web.soupe.dto;

import java.util.List;
import java.util.Map;

public class SoupeWebModel {
	private boolean isSuccess;
	private String status;

	private Object data;

	private Map<Object, Object> map;

	private List<Object> list;
	
	private String message ;

	public boolean isSuccess() {
		return isSuccess;
	}

	public SoupeWebModel(){
		this.isSuccess=false;
	}
   	
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object obj) {
		this.data = obj;
	}

	public Map<Object, Object> getMap() {
		return map;
	}

	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
