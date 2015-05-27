package common.controller;

import java.util.HashMap;
import java.util.Map;

/*
 * Controller로직을 처리할 Controller들이 FrontController에서 전달할 값을 저장할 클래스
 *  - View로 이동하기 위해 필요한 정보를 저장한다.
 *    - url : String -  이동할 URL
 *    - redirect : boolean - 요청 방식 (true:redirect방식, false:요청 디스패치방식)
 *    - attribute : Map - request scope에 binding할 Attribute들. (key-속성명, value-속성값)
 */
public class AttributeAndView {
	private String url;
	private boolean redirect;
	private Map<String, Object> attribute;
	//생성자
	public AttributeAndView(){}
	
	public AttributeAndView(String url, boolean redirect) {
		this.url = url;
		this.redirect = redirect;
	}
	//Attribute가 1개 이상일 경우 값 받는 생성자
	public AttributeAndView(String url, boolean redirect, 
																	Map<String, Object> attribute) {
		this.url = url;
		this.redirect = redirect;
		this.attribute = attribute;
	}
	//attribute가 하나일 경우 값 받는 생성자
	public AttributeAndView(String url, boolean redirect, String attributeName, Object attributeValue){
		this.url = url;
		this.redirect = redirect;
		
		attribute = new HashMap<String, Object>();
		attribute.put(attributeName, attributeValue);
	}
	//setter/getter

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public Map<String, Object> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, Object> attribute) {
		this.attribute = attribute;
	}
	//toString()

	@Override
	public String toString() {
		return "AttributeAndView [url=" + url + ", redirect=" + redirect
				+ ", attribute=" + attribute + "]";
	}
	
	
	
}











