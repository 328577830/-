package com.smo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.smo.pojo.Car;
import com.smo.service.CarService;
import com.smo.util.Pager;

//spring 注解此类是控制器(处理器)
@Controller
public class CarController {
	@Resource(name="carService")
	private CarService carService;
	
	@RequestMapping("carController_find")
	public ModelAndView find(@RequestParam(value="name") String name,
			@RequestParam(value="beginDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
			@RequestParam(value="endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
		 	Integer page = 1;
		    Integer rows = 3;
		    String sort = "id";
		    String order = "desc";
		    if(name!=null && name!=""){
		    	name = "%"+name+"%";
		    }
		    Integer pageno=(page-1)*rows;
		    Integer pagesize=page*rows;
		    Pager<Car> lists=carService.findPager(pageno, pagesize, sort, order, name, beginDate, endDate);
		    ModelAndView modelAndView=new ModelAndView("carlist");
		    modelAndView.addObject("lists", lists);
		    return modelAndView;
	}
	
	@RequestMapping("carController_save")
	public String save(Car car){
		if(car != null&&car.getId()!=null){
			carService.modify(car);
		}else{
			carService.add(car);
		}
		return "redirect:carController_list";
	}
	@RequestMapping("carController_findById")
	public ModelAndView findById(@RequestParam(value="id",required=true) Integer id){
		ModelAndView modelAndView=new ModelAndView("caredit");
		Car car=carService.find(id);
		modelAndView.addObject("car", car);
		return modelAndView;
	}
	
	@RequestMapping("carController_remove")
	public String remove(@RequestParam(value="id",required=true,defaultValue="0") Integer id){
		
		//表单参数可选,且设置默认值
		//value="id",required=false,defaultValue="0"
		carService.remove(id);
		return "redirect:carController_list";
	}
	
	@RequestMapping("carController_list")
	public String list(ModelMap modelMap){
		//访问模型(业务逻辑层,返回模型数据)
		List<Car> cars=carService.find();
		
		//modelMap相当于map,request作用域
		modelMap.put("cars", cars);
		//逻辑视图名
		return "carlist";
	}
}
