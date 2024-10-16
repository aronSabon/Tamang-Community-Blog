package com.appsoft.tmgsamaj.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appsoft.tmgsamaj.model.User;
import com.appsoft.tmgsamaj.model.UserPrincipal;
import com.appsoft.tmgsamaj.repository.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user==null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("usernot found");
		}
		return new UserPrincipal(user);
	}

}
