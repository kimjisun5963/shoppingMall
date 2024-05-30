package com.example.shoppingMall2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.IMemberDao;
import com.example.shoppingMall2.dto.Member;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	IMemberDao memberDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Member member = memberDao.findUsername(username);
		
		if(member != null) {
			return new CustomUserDetails(member);
		}
		
		return null;
	}

}
