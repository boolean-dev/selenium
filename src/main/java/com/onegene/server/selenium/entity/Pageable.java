/*
 * 
 * 
 * 
 */
package com.onegene.server.selenium.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 分页信息
 * 
 * 
 * 
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录数 */
	private static final int DEFAULT_PAGE_SIZE = 30;

	/** 最大每页记录数 */
	private static final int MAX_PAGE_SIZE = 1000;

	/** 页码 */
	private int pageNumber = DEFAULT_PAGE_NUMBER;

	/** 每页记录数 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** 搜索属性 */
	private String searchProperty;

	/** 搜索值 */
	private String searchValue;

	/** 排序属性 */
	private String orderProperty;

	/** 排序方向 */
	private Direction orderDirection;
	
	private Map<String,Object> filter;
	
	public enum Direction {

		/** 递增 */
		asc,

		/** 递减 */
		desc;
	}

	/**
	 * 初始化一个新创建的Pageable对象
	 */
	public Pageable() {
	}

	/**
	 * 初始化一个新创建的Pageable对象
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 */
	public Pageable(Integer pageNumber, Integer pageSize) {
		if (pageNumber != null && pageNumber >= 1) {
			this.pageNumber = pageNumber;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置页码
	 * 
	 * @param pageNumber
	 *            页码
	 */
	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 *            每页记录数
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 获取搜索属性
	 * 
	 * @return 搜索属性
	 */
	public String getSearchProperty() {
		return searchProperty;
	}

	/**
	 * 设置搜索属性
	 * 
	 * @param searchProperty
	 *            搜索属性
	 */
	public void setSearchProperty(String searchProperty) {
		this.searchProperty = searchProperty;
	}

	/**
	 * 获取搜索值
	 * 
	 * @return 搜索值
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * 设置搜索值
	 * 
	 * @param searchValue
	 *            搜索值
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * 获取排序属性
	 * 
	 * @return 排序属性
	 */
	public String getOrderProperty() {
		return orderProperty;
	}

	/**
	 * 设置排序属性
	 * 
	 * @param orderProperty
	 *            排序属性
	 */
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	/**
	 * 获取排序方向
	 * 
	 * @return 排序方向
	 */
	public Direction getOrderDirection() {
		return orderDirection;
	}

	/**
	 * 设置排序方向
	 * 
	 * @param orderDirection
	 *            排序方向
	 */
	public void setOrderDirection(Direction orderDirection) {
		this.orderDirection = orderDirection;
	}

	public Map<String,Object> getFilter() {
		return filter;
	}

	public void setFilter(Map<String,Object> filter) {
		this.filter = filter;
	}
	
}