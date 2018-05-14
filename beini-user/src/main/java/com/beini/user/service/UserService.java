package com.beini.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beini.user.entity.User;	

public interface UserService {
	/*查询*/
	Page<User> findAll(Pageable pageable);
	User findById(String id);
	/*更新*/
	User save(User bean);
	User update(User bean);
	void delete(String... id);
}
