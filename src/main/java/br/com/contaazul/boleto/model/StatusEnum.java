package br.com.contaazul.boleto.model;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
	
	PENDING("PENDING"),
	PAID("PAID"),
	CANCELED("CANCELED");
	
	private String status;
	
	private StatusEnum(String status) {
		this.status = status;
	}
	
	private static final Map<String, StatusEnum> map = new HashMap<String, StatusEnum>();
	static {
		for (StatusEnum d : StatusEnum.values()) {
			map.put(d.status, d);
		}
	}
	
	public static StatusEnum getStatusEnum(String status) {
		return map.get(status);
	}

	public String getValue() {
		return status;
	}
	
}
