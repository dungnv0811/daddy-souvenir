package com.dvnguyen.daddysouvenir.controller;

import com.dvnguyen.daddysouvenir.domain.Sale;
import com.dvnguyen.daddysouvenir.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class SaleController {

	private final SaleService saleService;

	public SaleController(SaleService saleService) {
		this.saleService = saleService;
	}

	public List<Sale> list() {
		List<Sale> listSale = saleService.list();
		return listSale;
	}

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Sale> listSale = saleService.list();
		model.addAttribute("listSale", listSale);
		return "index";
	}

	@RequestMapping("/new")
	public String showNewForm(Model model) {
		Sale sale = new Sale();
		model.addAttribute("sale", sale);
		return "new_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("sale") Sale sale) throws Exception {
		saleService.createOrUpdateEmployee(sale);
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") long id) throws Exception {
		ModelAndView mav = new ModelAndView("edit_form");
		saleService.deleteEmployeeById(id);
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("sale") Sale sale) throws Exception {
		saleService.createOrUpdateEmployee(sale);
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") long id) throws Exception {
		saleService.deleteEmployeeById(id);
		return "redirect:/";
	}

}