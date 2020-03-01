package com.controledeestoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controledeestoque.model.Produto;
import com.controledeestoque.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping("/add")
	public ModelAndView add(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/form");
		mv.addObject("produto", produto);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Produto produto, BindingResult result) {

		if (result.hasErrors()) {
			return add(produto);
		} else {
			produtoService.save(produto);
			return findAll();
		}
	}

	@GetMapping("/list")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("produto/list");
		mv.addObject("produtos", produtoService.findAll());
		return mv;
	}

	@GetMapping("/edit/{id}")
	private ModelAndView edit(@PathVariable("id") Long id) {
		Produto produto = produtoService.findOne(id);
		return add(produto);
	}

	@GetMapping("/delete/{id}")
	private ModelAndView delete(@PathVariable("id") Long id) {
		produtoService.delete(id);
		return findAll();
	}

	@PostMapping("/entrada")
	private ModelAndView entrada(@RequestParam("quantidade") int quantidade, @RequestParam("id") Long id) {
		Produto produto = produtoService.findOne(id);
		produto.setQuantidade(produto.getQuantidade() + quantidade);
		produtoService.save(produto);
		return findAll();
	}

	@PostMapping("/saida")
	private ModelAndView saida(@RequestParam("quantidade") int quantidade, @RequestParam("id") Long id) {
		Produto produto = produtoService.findOne(id);

		if (produto.getQuantidade() - quantidade < 0) {
			produto.setQuantidade(0);
		} else {
			produto.setQuantidade(produto.getQuantidade() - quantidade);
		}
		produtoService.save(produto);
		return findAll();
	}

}
