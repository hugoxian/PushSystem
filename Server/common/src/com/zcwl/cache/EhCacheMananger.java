package com.zcwl.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Ehcache管理器，单例.
 * 
 * 当用户个数比较大的时候，可尝试以下缓存策略：有一个用户索引列表，具体用户的信息再一个个缓存
 * 
 * @author Hugo
 * 
 */
public class EhCacheMananger {

	private static EhCacheMananger instance = new EhCacheMananger();

	private CacheManager cacheManager;

	private EhCacheMananger() {
		this.cacheManager = CacheManager.create(this.getClass().getResource(
				"/cache/ehcache.xml"));
	}

	public static EhCacheMananger getInstance() {
		return instance;
	}

	/**
	 * 取得一个cache对象
	 * 
	 * @param <V>
	 * @param cacheName
	 * @param key
	 * @return 如果缓存中有则返回对象，如无则返回NULL
	 */
	public <V> V get(String cacheName, Serializable key) {
		Element e = this.cacheManager.getCache(cacheName).get(key);
		// 反正出现取得空的情况
		if (e != null) {
			return (V) e.getValue();
		}
		return null;
	}

	/**
	 * 添加一个cache对象
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 * @return 返回旧的数据，如无则返回NULL
	 */
	public <V> V put(String cacheName, Serializable key, V value) {
		V old = this.get(cacheName, key);
		Element element = new Element(key, value);
		this.cacheManager.getCache(cacheName).put(element);
		return old;
	}

	/**
	 * 逐出一条数据
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public <V> V remove(String cacheName, Serializable key) {
		V old = this.get(cacheName, key);
		this.cacheManager.getCache(cacheName).remove(key);
		return old;
	}

	/**
	 * 取得缓存块的名称列表
	 * 
	 * @return
	 */
	public String[] getCacheNames() {
		return this.cacheManager.getCacheNames();
	}

	/**
	 * 返回Cache信息
	 * 
	 * @param cacheName
	 * @return
	 */
	public Cache getCacheByName(String cacheName) {
		return this.cacheManager.getCache(cacheName);
	}

}
