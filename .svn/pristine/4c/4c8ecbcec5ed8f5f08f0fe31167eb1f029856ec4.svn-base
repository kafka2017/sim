package com.amwell;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 打包部署启动类
 * @author 番茄很忙
 *
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    // 注意这里要指向原先用main方法执行的Application启动类
	    return builder.sources(SpringbootApplication.class);
	  }
}
