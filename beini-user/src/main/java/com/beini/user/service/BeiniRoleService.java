package com.beini.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beini.user.entity.BeiniRole;

public interface BeiniRoleService {
	/*查询*/
	Page<BeiniRole> findAll(Pageable pageable);
	BeiniRole findById(Integer id);
	/*更新*/
	BeiniRole save(BeiniRole bean);
	BeiniRole update(BeiniRole bean);
	void delete(Integer... id);
}
