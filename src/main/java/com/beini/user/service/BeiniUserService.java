package com.beini.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beini.user.entity.BeiniUser;

public interface BeiniUserService {
	/*查询*/
	Page<BeiniUser> findAll(Pageable pageable);
	BeiniUser findById(String id);
	/*更新*/
	BeiniUser save(BeiniUser bean);
	BeiniUser update(BeiniUser bean);
	void delete(String... id);
}
