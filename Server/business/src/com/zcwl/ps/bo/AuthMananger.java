package com.zcwl.ps.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwl.cache.EhCacheMananger;
import com.zcwl.ps.dao.NodeDao;
import com.zcwl.ps.dao.OperatorDao;
import com.zcwl.ps.dao.RoleDao;
import com.zcwl.ps.dao.SoftwareDao;
import com.zcwl.ps.dto.NodeDto;
import com.zcwl.ps.dto.OperatorDto;
import com.zcwl.ps.dto.RoleDto;
import com.zcwl.ps.dto.SoftwareDto;
import com.zcwl.tool.StringUtil;

/**
 * 
 * 节点管理者
 * 
 * @author Hugo
 * 
 */
@Service
public class AuthMananger {

	protected static final Logger LOG = LoggerFactory
			.getLogger(AuthMananger.class);

	@Autowired(required = true)
	private NodeDao nodeDao;

	@Autowired(required = true)
	private RoleDao roleDao;

	@Autowired(required = true)
	private OperatorDao operatorDao;

	@Autowired(required = true)
	private SoftwareDao softwareDao;

	/**
	 * 
	 * 取得所有节点信息，包括底下的二级节点
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<NodeDto> getAllNodes() throws Exception {
		List<NodeDto> nodes = EhCacheMananger.getInstance().get(
				EhCacheNames.webCache.name(), "AllNodes");

		if (nodes != null) {
			return nodes;
		}

		nodes = this.nodeDao.getNodesByParentId(0);
		// 现只有二级节点，如果扩展为N级节点，则用递归实现
		for (NodeDto node : nodes) {
			List<NodeDto> subNodes = this.nodeDao.getNodesByParentId(node
					.getId());
			node.setSubNodes(subNodes);
		}

		EhCacheMananger.getInstance().put(EhCacheNames.webCache.name(),
				"AllNodes", nodes);

		return nodes;
	}

	/**
	 * 取得角色列表，包括拥有的权限的节点列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RoleDto> getAllRoles() throws Exception {
		List<RoleDto> roles = EhCacheMananger.getInstance().get(
				EhCacheNames.webCache.name(), "AllRoles");

		if (roles != null) {
			return roles;
		}

		roles = this.roleDao.getRoles();

		List<NodeDto> allNodes = this.getAllNodes();

		for (RoleDto role : roles) {
			List<NodeDto> permitNodes = new ArrayList<NodeDto>();
			String permitIdsStr = role.getPermitIdsStr();
			String[] ids = permitIdsStr.split(",");
			// 循环取出有权限的节点
			for (String id : ids) {
				int tempId = Integer.parseInt(id);
				for (NodeDto node : allNodes) {
					if (tempId == node.getId()) {
						permitNodes.add(node);
						break;
					}
				}
			}

			role.setPermitNodes(permitNodes);
		}

		EhCacheMananger.getInstance().put(EhCacheNames.webCache.name(),
				"AllRoles", roles);

		return roles;
	}

	/**
	 * 新增一个操作者
	 * 
	 * @param operator
	 * @throws Exception
	 */
	public void addOperator(OperatorDto operator) throws Exception {
		this.operatorDao.add(operator);
		this.getAllOperators().add(operator);
		// 设置角色
		for (RoleDto role : this.getAllRoles()) {
			if (operator.getRoleId() == role.getId()) {
				operator.setRole(role);
				break;
			}
		}
	}

	/**
	 * 取得所有操作者信息，包括所属角色，节点权限等信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<OperatorDto> getAllOperators() throws Exception {
		List<OperatorDto> operators = EhCacheMananger.getInstance().get(
				EhCacheNames.webCache.name(), "AllOperators");

		if (operators != null) {
			return operators;
		}

		operators = this.operatorDao.getAllOperators();

		for (OperatorDto operator : operators) {
			int roleId = operator.getRoleId();
			if (roleId <= 0) {
				LOG.error("something wrong with the role id.");
				continue;
			}

			// 设置角色
			for (RoleDto role : this.getAllRoles()) {
				if (roleId == role.getId()) {
					operator.setRole(role);
					break;
				}
			}
			// 加载software列表
			try {
				List<SoftwareDto> softwares = this
						.getSoftwaresByOperatorId(operator.getId());
				if (softwares != null && softwares.size() > 0) {
					operator.setSoftwares(softwares);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		EhCacheMananger.getInstance().put(EhCacheNames.webCache.name(),
				"AllOperators", operators);

		return operators;

	}

	/**
	 * 新增应用
	 * 
	 * @param operator
	 * @param software
	 * @throws Exception
	 */
	public void addSoftware(OperatorDto operator, SoftwareDto software)
			throws Exception {
		software.setOperatorId(operator.getId());
		software.setAppKey(StringUtil.generateUuid());
		software.setCreateDate(new Date());

		this.softwareDao.add(software);

		// 如果列表List为空，则新建一个
		if (operator.getSoftwares() == null) {
			operator.setSoftwares(new ArrayList<SoftwareDto>());
		}

		operator.getSoftwares().add(software);
	}

	/**
	 * 更新应用
	 * 
	 * @param operator
	 * @param software
	 */
	public void updateSoftware(OperatorDto operator, SoftwareDto software)
			throws Exception {
		// 更新数据库信息
		this.softwareDao.update(software);
		List<SoftwareDto> softwares = operator.getSoftwares();
		if (softwares != null) {
			// 更新缓存信息
			for (SoftwareDto temp : softwares) {
				if (temp.getId() == software.getId()) {
					temp.setName(software.getName());
					temp.setPackageName(software.getPackageName());
					temp.setStatus(software.getStatus());
					temp.setWelcomeMsg(software.getWelcomeMsg());
				}
			}
		}
	}

	/**
	 * 登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public OperatorDto login(String account, String password) throws Exception {
		return this.operatorDao.login(account, password);
	}

	/**
	 * 更新登录时间
	 * 
	 * @param operatorId
	 * @param date
	 * @throws Exception
	 */
	public void updateLoginDate(int operatorId, Date date) throws Exception {
		this.operatorDao.updateLoginDate(operatorId, date);
	}

	/**
	 * 取得software列表
	 * 
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	public List<SoftwareDto> getSoftwaresByOperatorId(int operatorId)
			throws Exception {
		return this.softwareDao.getSoftwaresByOperatorId(operatorId);
	}

	/**
	 * 
	 * @param appKey
	 * @return
	 * @throws Exception
	 */
	public int getOpertorIdByAppKey(String appKey) throws Exception {
		return this.softwareDao.getOpertorIdByAppKey(appKey);
	}

}
