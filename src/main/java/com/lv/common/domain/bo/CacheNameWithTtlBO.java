package com.lv.common.domain.bo;


/**
 * 通过 cacheName 配置 和 时间告诉缓存多久清楚一遍
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/8 11:57 AM
 */
public class CacheNameWithTtlBO {

	private String cacheName;

	private Integer ttl;

	public CacheNameWithTtlBO(String cacheName, Integer ttl) {
		this.cacheName = cacheName;
		this.ttl = ttl;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public Integer getTtl() {
		return ttl;
	}

	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

	@Override
	public String toString() {
		return "CacheNameWithTtlBO{" + "cacheName='" + cacheName + '\'' + ", ttl=" + ttl + '}';
	}

}
