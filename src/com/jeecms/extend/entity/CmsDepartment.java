package com.jeecms.extend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.common.hibernate3.HibernateTree;
import com.jeecms.common.hibernate3.PriorityInterface;
import com.jeecms.extend.entity.base.BaseCmsDepartment;

public class CmsDepartment extends BaseCmsDepartment implements
		HibernateTree<Integer>, PriorityInterface {
	private static final long serialVersionUID = 1L;

	public String getName() {
		CmsDepartmentExt ext = getDepartmentExt();
		if (ext != null) {
			return ext.getName();
		} else {
			return null;
		}
	}

	/**
	 * 获得深度
	 * 
	 * @return 第一层为0，第二层为1，以此类推。
	 */
	public int getDeep() {
		int deep = 0;
		CmsDepartment parent = getParent();
		while (parent != null) {
			deep++;
			parent = parent.getParent();
		}
		return deep;
	}

	/**
	 * @see HibernateTree#getLftName()
	 */
	public String getLftName() {
		return DEF_LEFT_NAME;
	}

	/**
	 * @see HibernateTree#getParentName()
	 */
	public String getParentName() {
		return DEF_PARENT_NAME;
	}

	/**
	 * @see HibernateTree#getRgtName()
	 */
	public String getRgtName() {
		return DEF_RIGHT_NAME;
	}

	/**
	 * @see HibernateTree#getParentId()
	 */
	public Integer getParentId() {
		CmsDepartment parent = getParent();
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}

	/**
	 * 应该是可以支持不同的条件下 树的排列不一样
	 * 
	 * @see HibernateTree#getTreeCondition()
	 */
	public String getTreeCondition() {
		return "";
	}

	public void init() {
		if (this.getPriority() == null) {
			this.setPriority(10);
		}
		if (this.getDisplay() == null) {
			this.setDisplay(true);
		}
	}

	public static List<CmsDepartment> getListForSelect(
			List<CmsDepartment> topList) {
		List<CmsDepartment> list = new ArrayList<CmsDepartment>();
		for (CmsDepartment department : topList) {
			addChildToList(list, department);

		}
		return list;
	}

	private static void addChildToList(List<CmsDepartment> list,
			CmsDepartment department) {
		list.add(department);
		Set<CmsDepartment> listChild = department.getChild();
		for (CmsDepartment child : listChild) {
			addChildToList(list, child);
		}
	}
}
