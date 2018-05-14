package com.beini.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beini.user.entity.User;
import com.beini.user.repository.UserRepository;
import com.beini.user.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository repository;
	@Override
	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public User findById(String id) {
		return repository.findOne(id);
	}

	@Override
	public User save(User bean) {
		return repository.save(bean);
	}

	@Override
	public User update(User bean) {
		return repository.save(bean);
	}

	@Override
	public void delete(String... id) {
		try {
			for(String str:id) {
				repository.delete(str);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

}
