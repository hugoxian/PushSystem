package com.zcwl.ps.vo;

/**
 * 
 * 缓存块给展示层用的实体
 * 
 * @author Hugo
 * 
 */
public class CacheVo {
	/**
	 * 缓存块名称
	 */
	private String name;
	/**
	 * 内存中缓存个数
	 */
	private long memoryStoreSize;
	/**
	 * 硬盘中缓存个数
	 */
	private long diskStoreSize;

	/**
	 * 占用内存大小，单位字节
	 */
	private long memorySize;

	/**
	 * 占用硬盘大小,单位字节
	 */
	private long diskSize;
	/**
	 * 缓存读取的命中次数
	 */
	private long cacheHits;
	/**
	 * 缓存读取的错失次数
	 */
	private long cacheMisses;

	public CacheVo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMemoryStoreSize() {
		return memoryStoreSize;
	}

	public void setMemoryStoreSize(long memoryStoreSize) {
		this.memoryStoreSize = memoryStoreSize;
	}

	public long getDiskStoreSize() {
		return diskStoreSize;
	}

	public void setDiskStoreSize(long diskStoreSize) {
		this.diskStoreSize = diskStoreSize;
	}

	public long getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(long memorySize) {
		this.memorySize = memorySize;
	}

	public long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	public long getCacheHits() {
		return cacheHits;
	}

	public void setCacheHits(long cacheHits) {
		this.cacheHits = cacheHits;
	}

	public long getCacheMisses() {
		return cacheMisses;
	}

	public void setCacheMisses(long cacheMisses) {
		this.cacheMisses = cacheMisses;
	}

}
