package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Dao.EmployeeDao;
import model.EmployeeBean;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	EmployeeDao dao;

	@RequestMapping("/empform")
	public ModelAndView showform() {
		return new ModelAndView("empform", "command", new EmployeeBean());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("emp") EmployeeBean emp) {
		dao.save(emp);
		return new ModelAndView("redirect:/viewemp");
	}

	@RequestMapping("/viewemp")
	public ModelAndView viewemp() {
		List<EmployeeBean> list = dao.getAllEmployees();
		return new ModelAndView("viewemp", "list", list);
	}

	@RequestMapping(value = "/editemp/{id}")
	public ModelAndView edit(@PathVariable int id) {
		EmployeeBean emp = dao.getEmpById(id);
		return new ModelAndView("empeditform", "command", emp);
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("emp") EmployeeBean emp) {
		dao.update(emp);
		return new ModelAndView("redirect:/viewemp");
	}

	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		dao.delete(id);
		return new ModelAndView("redirect:/viewemp");
	}
}
