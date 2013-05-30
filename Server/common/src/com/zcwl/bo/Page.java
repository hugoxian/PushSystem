package com.zcwl.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询
 * 
 * @author Hugo
 * 
 * @param <T>
 */
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9089191871548199484L;

	/**
	 * 查询当前页，页号从1开始
	 */
	protected int pageNo = 1;
	/**
	 * 每页的大小
	 */
	protected int pageSize = 15;
	/**
	 * 查询返回的DTO记录集
	 */
	private List<T> result = null;
	/**
	 * 总记录数
	 */
	private int totalCount = -1;

	private boolean isFirst = false;

	private boolean isLast = false;

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo > 0) {
			this.pageNo = pageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获取记录起始地址
	 * 
	 * @return
	 */
	public int getOffset() {
		return this.pageSize * (this.pageNo - 1);
	}

	/**
	 * 获取第pageNo页的起始记录的行号
	 * 
	 * @return
	 */
	public int getBeginRowNumByPage() {
		return (getPageNo() - 1) * getPageSize() + 1;
	}

	/**
	 * 获取第pageNo页的最后记录的行号
	 * 
	 * @return
	 */
	public int getEndRowNumByPage() {
		return getPageNo() * getPageSize();
	}
}
