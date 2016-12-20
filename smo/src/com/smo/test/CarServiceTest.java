package com.smo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smo.pojo.Car;
import com.smo.service.CarService;
import com.smo.util.Pager;

public class CarServiceTest {
	private CarService carService;
	@Test
	  public void findPager() {
	    // easyui ����Ĳ���
	    Integer page = 1;
	    Integer rows = 2;
	    String sort = "name";
	    String order = "desc";

	    // �������� (��ѡ)
	    String name = null;
	    Date beginDate = null;
	    Date endDate = null;

	     //name = "%��%";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	      beginDate = sdf.parse("2016-11-1");
	      endDate = sdf.parse("2016-12-14");
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }

	    // �����ҳ����ʼҳ�ͽ���ҳ
	    Integer pageno = (page - 1) * rows;
	    Integer pagesize = page * rows;

	    Pager<Car> pager = carService.findPager(pageno, pagesize, sort, order, name, beginDate, endDate);

	    System.out.println("��¼����: " + pager.getTotal());
	    for (Car car : pager.getRows()) {
	      System.out.println(car.getName() + " "+car.getPrice());
	    }
	  }
	@Test
	public void remove(){
		int count=carService.remove(5);
		if(count>0){
			System.out.println("ɾ���ɹ�");
		}else{
			System.out.println("ɾ��ʧ��");
		}
	}
	@Test
	public void modify(){
		Car car=new Car();
		car.setId(4);
		car.setName("��˹��˹");
		car.setSaleDate(new Date());
		car.setPrice(20000000d);
		carService.modify(car);
	}
	@Test
	public void findById(){
		Car car=carService.find(6);
		System.out.println(car.getName()+" "+car.getSaleDate()+" "+car.getPrice());
	}
	@Test
	public void find(){
		List<Car> list=carService.find();
		for(Car car:list){
			System.out.println(car.getName()+" "+car.getSaleDate()+" "+car.getPrice());
		}
	}
	@Test
	public void add(){
		Car car=new Car();
		car.setName("xxx");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String saleDate=sdf.format(date);
		Date saleDate1=null;
		try {
			saleDate1=sdf.parse(saleDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		car.setSaleDate(saleDate1);
		car.setPrice(10000d);
		carService.add(car);
	}
	
	@Before
	public void init(){
		ApplicationContext cxt=new ClassPathXmlApplicationContext("applicationContext.xml");
		carService=cxt.getBean("carService",CarService.class);
	}
}
