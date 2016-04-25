package com.jeecms.extend.manager;

import java.util.List;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.extend.entity.CmsDepartment;
import com.jeecms.extend.entity.CmsDepartmentExt;

public interface CmsDepartmentMng {

	/**
	 * 根据id查找部门
	 * 
	 * @param id 
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public CmsDepartment findById(Integer id);

	/**
	 * 获得顶级部门列表
	 * 
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public List<CmsDepartment> getTopList();

	/**
	 * 获得子级列表
	 * 
	 * @param parentId 上级部门id
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public List<CmsDepartment> getChildList(Integer parentId);

	/**
	 * 保存部门
	 * 
	 * @param department
	 * @param ext
	 * @param parentId
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public CmsDepartment save(CmsDepartment department, CmsDepartmentExt ext,Integer parentId);

	/**
	 * 更新部门
	 * 
	 * @param department
	 * @param ext
	 * @param parentId
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public CmsDepartment update(CmsDepartment department, CmsDepartmentExt ext,Integer parentId);

	/**
	 * 更新排列顺序
	 * 
	 * @param wids
	 * @param priority
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public CmsDepartment[] updatePriority(Integer[] wids, Integer[] priority);

	/**
	 * 根据id集合删除部门，会级联删除子级部门
	 * 
	 * @param wids 要删除的部门id集合
	 * @return
	 * author:聂箫
	 * date：2015年12月29日
	 */
	public CmsDepartment[] deleteByIds(Integer[] wids);

	public List<CmsDepartment> getChushiList();

	public List<CmsDepartment> getXuyuanduiList();
	/**
	 * 根据部门类别获取部门
	 * @param @param pattern
	 * @param @return
	 * @return List<CmsDepartment>
	 * @author zhengpp
	 * @date 2016年3月2日 下午2:52:15
	 */
	public List<CmsDepartment> getListByCategory(String pattern);
	
	public CmsDepartment[] updatePersonCount(Integer[] wids, Integer[] personCount);

	long getListByCategorySum(String pattern);

}
