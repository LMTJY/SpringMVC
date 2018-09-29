# SpringMVC
搭建SpringMVC的步骤
### 搭建SpringMVC的步骤

##### 方式一：老版的搭建方式**BeanNameUrlHandlerMapping**

##### 1、创建web项目，导入提供的Spring包

##### 2、配置web.xml（前端控制器，编码格式）

```
<servlet>      //将所有请求分配给DispatcherServlet
  	<servlet-name>hello</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  </servlet>
  <servlet-mapping>
  	<servlet-name>hello</servlet-name>
  	<url-pattern>/</url-pattern>
  </servlet-mapping>
  <filter> //设置全局编码格式
  	<filter-name>encoder</filter-name>
  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  	
  	<init-param>
  		<param-name>encoding</param-name>
  		<param-value>utf-8</param-value>
  	</init-param>
  	<init-param>
  		<param-name>forceEncoding</param-name>
  		<param-value>true</param-value>
  	</init-param>
  </filter>
  <filter-mapping>
  	<filter-name>encoder</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
```

##### 3、复制XX-servlet.xml到项目中（导入SpringMVC的配置文件）

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.1.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd">
	//配置伪地址的映射
	<bean name="/welcome.html" class="controller.WelcomeController"></bean>

	<bean id="viewResolver"  
		class="org.springframework.web.servlet.view.UrlBasedViewResolver">
		<property name="prefix" value="/myjsp/" />//配置对应的jsp页面地址
		<property name="suffix" value=".jsp" />
		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>	
	</bean>
</beans>	
```

##### 4、编写控制层

```
public class WelcomeController extends AbstractController {//继承类

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		System.out.println("welcome......welcome.....");
		return new ModelAndView("welcome");
	}

}
```

##### 方式二：最常用的搭建方式**DefaultAnnotationHandlerMapping**（使用注解型搭建）

##### *解决：控制层耦合的问题，每页页面请求只需要一个方法就可以做跳转,*

##### 1、修改SpringMVC的配置文件 改用扫描的方式

```
	<context:component-scan base-package="controller"></context:component-scan>
```

##### 2、在控制层采用注解标记

```
@Controller()
public class HelloWordAction {
	
	@RequestMapping("hello.html")    //浏览器访问地址
	public String hello(){
		return "hello";
	}
}
```

##### 3、改进静态文件

```
<servlet-mapping>
  	<servlet-name>hello</servlet-name>
  	<url-pattern>*.html</url-pattern>
</servlet-mapping>
```

#### 控制层接受界面提交的数据

###### 方式一：通过注解的方式传递参数

```
@RequestMapping("log")  
	public String login(@RequestParam(value="uname")String name,@RequestParam(value="upwd")String pwd){
		return "login";
	}
```

###### 方式二：直接传递参数    Model 往视图层传递参数

```
@RequestMapping("log")
	public String log(String uname,String upwd,Model model){
		if(uname.equals("LMT") && upwd.equals("123")){
			model.addAttribute("name",uname);
			return "success";
		}else{
			return "login";
		}
	}
```

