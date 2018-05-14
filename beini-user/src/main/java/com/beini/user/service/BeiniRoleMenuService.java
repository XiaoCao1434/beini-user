package com.beini.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beini.user.entity.BeiniRoleMenu;

public interface BeiniRoleMenuService {
	/*查询*/
	Page<BeiniRoleMenu> findAll(Pageable pageable);
	BeiniRoleMenu findById(Integer id);
	/*更新*/
	BeiniRoleMenu save(BeiniRoleMenu bean);
	BeiniRoleMenu update(BeiniRoleMenu bean);
	void delete(Integer... id);
}
