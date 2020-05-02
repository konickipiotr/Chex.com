package com.chex.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chex.model.user.UserAuth;

public class ChexUserDetails implements UserDetails {
	private static final long serialVersionUID = 1194451675520758014L;
	
	private UserAuth userAuth;

	public ChexUserDetails(UserAuth userAuth) {
		this.userAuth = userAuth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+ userAuth.getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return userAuth.getPassword();
	}

	@Override
	public String getUsername() {
		return userAuth.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userAuth.getStatus() == 2;
	}

}
