package com.example.springboot04.controller;

import com.example.springboot04.dao.DepartmentDao;
import com.example.springboot04.dao.EmployeeDao;
import com.example.springboot04.entities.Department;
import com.example.springboot04.entities.Employee;
import com.example.springboot04.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;

/**
 * @className: EmpController
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/12 4:46
 * @Version: 1.0
 */
@Controller
public class EmpController {
	// 参考文档：spring 的三种注入方式：属性注入，构造函数注入和setter注入
	@Autowired
	EmployeeDao employeeDao;
	// 注入类DepartmentDao
	@Autowired
	DepartmentDao departmentDao;


	// 返回所有员工列表的页面
	// public String list(DepartmentDao departmentDao, Collection<Department> collection) {
	// 	// 获取存放于DepartmentDAO类中的员工列表数据
	// 	collection = departmentDao.getDepartments();
	// 	return "emp/list";
	// }
	@GetMapping("/emps")
	public String list(Model model) {
		// 获取存放于DepartmentDAO类中的员工列表数据
		Collection<Employee> employees = employeeDao.getAll();
		model.addAttribute("emps", employees);
		return "emp/list";
	}

	// 添加员工时，先来到可供填充数据的页面
	@GetMapping("/emp")
	public String toAddPage(Model model) {
		// 来到添加页面之前的准备，查询处员工的所有部门，并渲染在Department的列表项option上
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		// 来到添加页面
		return "emp/add";
	}

	// 正式提交，添加的员工信息，post方式
	/*
	 * 逻辑：提交后，要回到列表页面list.html(/emps)
	 * 语法：
	 * redirect:/请求地址  表示重定向到 请求地址
	 * forward:/请求地址  表示转发到 请求地址
	 *
	 * 我们需要获取表单中提交的各选项的值，它映射的是Employee实体类
	 * 1.在doAdd中传入参数Employee employee
	 * 2.spring mvc会自动将提交的请求参数和对象属性进行一一绑定；要求请求参数的名称（表单中的name属性值）
	 * 和java bean对象里的属性（Employee实体类的成员属性）一一对应
	 * 测试一下：
	 * System.out.println("保存的员工信息"+employee);
	 *
	 * bug:添加员工信息时，birth日期格式问题？
	 * 2012/12/12  正常重定向到/emps
	 * 2012-12-12  报错，status 400
	 *
	 * 原因：spring boot默认按照/方式分割日期的，其他格式2012-12-12或者2012.12.12 需要转换成/格式
	 * 1.查看 WebMvcAutoConfiguration类,spring boot默认使用过2012/12/12格式
	 * 2.也可以在配置文件中，自己配置日期的默认输入格式
	 *
	 * 文件 application.properties
	 * spring.mvc.format.date=yyyy-MM-dd
	 *
	 **/
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		// 1.拿到form表单提交的数据
		System.out.println("保存的员工信息" + employee);
		// 2.调用EmployeeDao中save方法保存
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	// 修改员工信息，先回显数据，复用add.html页面
	@GetMapping("/emp/{empId}")
	public String toModifyPage(@PathVariable("empId") Integer empId, Model model) {
		// @PathVariable获取empId值，表示要修改的那个item的empId值
		Employee employee = employeeDao.get(empId);
		model.addAttribute("modEmp", employee);
		// 同时，也要处理部门下拉框中的选项渲染问题
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		return "emp/add";
	}

	// 注：修改员工信息后，点击“修改”按钮后，也跳转至列表页面list,并没有新增一条数据，这是一个put请求；不同于“添加员工”的提交，是post请求
	@PutMapping("/emp")
	public String doModify(Employee employee) {
		System.out.println("保存的员工信息" + employee);
		// 2.调用EmployeeDao中save方法保存
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	// 删除某条员工信息
	@DeleteMapping("/emp/{id}")
	public String delEmp(@PathVariable Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}

	// 处理异常
	// 访问：localhost:8080/crud/hel?user=aaa,抛出这个自定义异常
	/*
	 * 在main标签内容区显示以下异常
	 * timestamp:Tue May 14 20:18:05 CST 2024
	 * status:500
	 * exception:com.example.springboot04.exception.UserNotExistException
	 *  message:用户不存在
	 *
	 * 分析：其中message信息，就是自定义的，自己定制
	 *
	 * */
	// 访问：localhost:8080/crud/hel?user=111,正常显示Hello World
	@RequestMapping("/hel")
	@ResponseBody
	public String hel(@PathParam("user") String user) {
		if (user.equals("aaa")) {
			// 	抛出异常
			throw new UserNotExistException("用户不存在");
		}
		return "Hello World";
	}
}

