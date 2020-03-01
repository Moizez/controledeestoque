package com.controledeestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controledeestoque.model.Produto;
import com.controledeestoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto save(Produto produto) {
		return repository.saveAndFlush(produto);
	}

	public Produto findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}
	

}
