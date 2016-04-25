package com.jeecms.extend.dao;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.extend.entity.CmsDepartmentExt;

public interface CmsDepartmentExtDao {

	CmsDepartmentExt save(CmsDepartmentExt bean);

	CmsDepartmentExt updateByUpdater(Updater<CmsDepartmentExt> updater);

}
