package com.jeecms.extend.manager;

import com.jeecms.extend.entity.CmsDepartment;
import com.jeecms.extend.entity.CmsDepartmentExt;

public interface CmsDepartmentExtMng {

	CmsDepartmentExt save(CmsDepartmentExt departmentExt, CmsDepartment department);

	CmsDepartmentExt update(CmsDepartmentExt ext);

}
