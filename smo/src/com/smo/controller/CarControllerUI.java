package com.smo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smo.pojo.Car;
import com.smo.service.CarService;
import com.smo.util.JsonDateValueProcessor;
import com.smo.util.Pager;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 页面控制器/处理器 for easyui
 * 
 * @author Administrator
 *
 */

@Controller
public class CarControllerUI {

	@Resource(name = "carService")
	private CarService carService;

	@RequestMapping("carui_save")
	public void save(Car car, HttpServletResponse response) {
		int count = 0;
		if (car != null && car.getId() != null) {
			count = carService.modify(car);
		} else {
			count = carService.add(car);
		}

		try {
			PrintWriter out = response.getWriter();
			out.println(count);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("carui_remove")
	public void remove(
			@RequestParam(value = "ids", required = true) String ids,
			HttpServletResponse response) {
		//1,2,3,4
		String[] sids=ids.split(",");
		int count=0;
		for(int i=0;i<sids.length;i++){
			Integer id=NumberUtils.parseNumber(sids[i], Integer.class);
			count+=carService.remove(id);
		}
		try {
			PrintWriter out = response.getWriter();
			out.println(count);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("carui_findById")
	public ModelAndView findById(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("careditui");
		Car car = carService.find(id);
		modelAndView.addObject("car", car);
		return modelAndView;
	}

	@RequestMapping("carui_list")
	public void list(@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "rows", required = true) Integer rows,
			@RequestParam(value = "sort", required = true) String sort,
			@RequestParam(value = "order", required = true) String order,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "beginDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			HttpServletResponse response) {
		if (!StringUtils.isEmpty(name)) {
			name = "%" + name + "%";
		}
		Integer pageno = (page - 1) * rows;
		Integer pagesize = page * rows;
		Pager<Car> pager = carService.findPager(pageno, pagesize, sort, order, name, beginDate, endDate);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSON json = JSONSerializer.toJSON(pager, jsonConfig);
		try {
			PrintWriter out = response.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Car car : pager.getRows()) {
			System.out.println(car.getName() + " " + car.getPrice());
		}
	}
}
