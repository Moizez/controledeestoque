package com.controledeestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controledeestoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
