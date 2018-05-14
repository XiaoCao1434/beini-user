package com.beini.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beini.user.entity.BeiniMenu;

public interface BeiniMenuService {
	/*查询*/
	Page<BeiniMenu> findAll(Pageable pageable);
	BeiniMenu findById(Integer id);
	/*更新*/
	BeiniMenu save(BeiniMenu bean);
	BeiniMenu update(BeiniMenu bean);
	void delete(Integer... id);
}
