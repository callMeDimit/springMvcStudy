package com.dimit.controler;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dimit.entity.Person;

@Controller
@RequestMapping("/mvc")
public class HelloWorldController {
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	/**
	 * RequestMapping 注解为控制器指定可以处理哪些 URL 请求
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello() {
		logger.info("进入hello方法...");
		return "hello";
	}

	/**
	 * 自动参数匹配
	 * 
	 * @param name
	 * @param age
	 * @return 访问URL
	 *         http://localhost:8080/springMvcStudy/mvc/person?name=zhangsan&age=12
	 */
	@RequestMapping("/person")
	public String toPerson(String name, double age) {
		System.out.println(name + " " + age);
		return "hello";
	}

	/**
	 * 自动装箱
	 * 
	 * @param p
	 * @return
	 * 
	 *         访问URL
	 *         http://localhost:8080/springMvcStudy/mvc/person1?name=zhangsan&age=12
	 */
	@RequestMapping("/person1")
	public String toPerson(Person p) {
		logger.info(p.getName() + " ------ " + p.getAge());
		return "hello";
	}

	/**
	 * 时间日期处理
	 * 
	 * @param date
	 * @return 访问url
	 *         http://localhost:8080/springMvcStudy/mvc/date?date=2015-02-01
	 */
	@RequestMapping(value = "/date", method = RequestMethod.GET)
	public String date(Date date) {
		System.out.println(date);
		return "hello";
	}

	// At the time of initialization,convert the type "String" to type "date"
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 向前端传递数据
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/showBack")
	public String showPerson(Map<String, Object> map) {
		Person p = new Person();
		map.put("p", p);
		p.setAge(20);
		p.setName("jayjay");
		return "showBack";
	}
	
	/**
	 * 重定向
	 * @return
	 */
	@RequestMapping("/redirect")
    public String redirect(){
		logger.info("进入重定向方法...");
        return "redirect:hello";
    }
	
	/**
	 * RequestParam注解用于指定参数名称
	 * @param name
	 * @param pw
	 */
	@RequestMapping(value = "/getPerson", method = RequestMethod.POST)
	public void getPerson( @RequestParam(value="name") String name,PrintWriter pw){
		System.out.println(name);
		pw.write("hello,"+name);
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
    public String upload(HttpServletRequest req) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");      
        System.out.println(req.getSession().getServletContext().getRealPath("/")+
                "upload/");
        FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
                "upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return "hello";
    }
}
