package com.blogapp.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogapp.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = null;
		String username = null;

//		Get Header form request
		String requestHeader = request.getHeader("Authorization");

//		Get token from header and username form token
		if (Objects.nonNull(requestHeader) && requestHeader.startsWith("Bearer")) {

			token = requestHeader.substring(7);
			try {
				username = jwtTokenUtil.extractUsername(token);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Request header is null.");
		}

//		Load UserDetails by using username and then validate the given token
//		after successfully validating token set Authentication Object in Context
		if (Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, userDetails))) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Unvalid Token");
			}
		} else {
			System.out.println("Username is null.");
		}
		
		filterChain.doFilter(request, response);

	}

}
