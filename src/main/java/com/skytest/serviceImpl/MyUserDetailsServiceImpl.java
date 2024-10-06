package com.skytest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skytest.model.User;
import com.skytest.model.UserPrincipal;
import com.skytest.repository.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		System.out.println(user);
		System.out.println("usrname: "+ username);
		if(user==null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("usernot found");
		}
		
		
		return new UserPrincipal(user);
	}

}
