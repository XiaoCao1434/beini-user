package com.beini.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beini.user.entity.BeiniUserRole;

public interface BeiniUserRoleService {
	/*查询*/
	Page<BeiniUserRole> findAll(Pageable pageable);
	BeiniUserRole findById(Integer id);
	/*更新*/
	BeiniUserRole save(BeiniUserRole bean);
	BeiniUserRole update(BeiniUserRole bean);
	void delete(Integer... id);
}
