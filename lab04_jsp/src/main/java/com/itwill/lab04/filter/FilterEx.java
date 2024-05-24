package com.itwill.lab04.filter;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class FilterEx
 */
public class FilterEx extends HttpFilter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
	
    public FilterEx() {
    	System.out.println("FilterEx 생성");
    }

	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() { // 필터가 메모리에서 삭제될 때(서버가 종료될 때) 호출됨. (딱 한 번)
		System.out.println("FilterEx::destroy() 호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("FilterEx chain.doFilter() 호출 전"); // 서블릿 메서드가 호출되기 전 해야 할일

		// pass the request along the filter chain
		chain.doFilter(request, response); // 이게 없으면 서블릿 메서드를 호출하지 X
		
		System.out.println("FilterEx chain.doFilter() 호출 후"); // 서블릿 메서드가 호출된 후 해야 할일
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException { // 서버가 시작될 때(초기화될 때) 호출됨. (딱 한 번)
		System.out.println("FilterEx::init() 호출");
	}

}
