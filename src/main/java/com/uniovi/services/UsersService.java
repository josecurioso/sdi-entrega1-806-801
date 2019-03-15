package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public List<User> getUsers() {
		return usersRepository.getUsersNotDeleted();
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setMoney(100.0);
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
		// Only mark as deleted with the commented code
//		User u = getUser(id);
//		u.setIsDeleted(true);
//		usersRepository.save(u);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);

	}

	public void deleteAll() {
		usersRepository.deleteAll();
	}

}
