package com.zcwl.ps.bo;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zcwl.cache.EhCacheMananger;
import com.zcwl.ps.vo.CacheVo;
import com.zcwl.ps.vo.IoSessionVo;
import com.zcwl.ps.vo.PerformanceVo;
import com.zcwl.tool.DateUtil;
import com.zcwl.tool.StringUtil;

/**
 * 
 * PS信息管理者
 * 
 * @author Hugo
 * 
 */
@Service
public class PsMananger {

	private static final Logger LOG = LoggerFactory.getLogger(PsMananger.class);

	private String errorLogPath;

	private CacheVo[] caches = null;
	
	public static final DateUtil dateUtil = new DateUtil();
	

	/**
	 * 性能数据存储map
	 */
	private static Map<String, PerformanceVo> performanceMaps = new ConcurrentHashMap<String, PerformanceVo>();

	public void add(String requestUri, int processTime) throws Exception {
		PerformanceVo performance = performanceMaps.get(requestUri);
		if (performance == null) {
			performance = new PerformanceVo();
			performance.setRequestUri(requestUri);
			performanceMaps.put(requestUri, performance);
		}

		int times = performance.getTimes() + 1;
		performance.setTimes(times);

		int totalProcessTime = performance.getTotalProcessTime() + processTime;
		performance.setTotalProcessTime(totalProcessTime);

		performance.setLastProcessTime(processTime);
	}

	/**
	 * 取得性能统计数据
	 * 
	 * @return
	 */
	public Map<String, PerformanceVo> getPerformances() throws Exception {
		return performanceMaps;
	}

	/**
	 * 取得缓存信息数据
	 * 
	 * @return
	 */
	public CacheVo[] getCacheInfos() throws Exception {
		String[] cacheNames = EhCacheMananger.getInstance().getCacheNames();
		if (cacheNames != null && cacheNames.length > 0) {
			// 尽可能减少new带来的开销
			if (caches == null) {
				caches = new CacheVo[cacheNames.length];
			}
			for (int i = 0; i < cacheNames.length; i++) {
				if (caches[i] == null) {
					caches[i] = new CacheVo(cacheNames[i]);
				}
				Cache cache = EhCacheMananger.getInstance().getCacheByName(
						cacheNames[i]);
				caches[i].setCacheHits(cache.getStatistics().getCacheHits());
				caches[i]
						.setCacheMisses(cache.getStatistics().getCacheMisses());
				caches[i].setMemorySize(cache.calculateInMemorySize());
				caches[i].setDiskSize(cache.calculateOnDiskSize());
				caches[i].setMemoryStoreSize(cache.getMemoryStoreSize());
				caches[i].setDiskStoreSize(cache.getDiskStoreSize());
			}
		}
		return caches;
	}

	/**
	 * 取得socketIoSession信息数据
	 */
	public List<IoSessionVo> getIoSessionInfos() throws Exception {
		return null;
	}

	/**
	 * 取得日志文件路径
	 * 
	 * @return
	 */
	public String getErrorLogPath() {
		if (this.errorLogPath == null) {
			StringBuffer sb = new StringBuffer(PSSystemContext.getInstance()
					.getContainerPath());
			sb.append(File.separator).append("logs").append(File.separator)
					.append("ps").append(File.separator).append("error.log");
			errorLogPath = sb.toString();
		}
		return errorLogPath;

	}

	/**
	 * 
	 * 取得错误日志,编码默认用utf-8
	 * 
	 * @return
	 */
	public String getErrorLog(String errorLogPath) throws Exception {
		return StringUtil.readFile2Text(errorLogPath, "utf-8");
	}
}
