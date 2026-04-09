package com.example.demo.Service;

import com.example.demo.Model.ProdutoModel;
import com.example.demo.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository rep) {
        repository = rep;
    }

    public ProdutoModel adicionaProduto(ProdutoModel prod) {
        return repository.save(prod);
    }

    public List<ProdutoModel> listaProdutos() {
        return repository.findAll();
    }

    public Optional<ProdutoModel> listaProdutoPorId(Long id) {
        return repository.findById(id);
    }

    public void deletaProdutoPorId(Long id) {
         repository.deleteById(id);
    }

    public Optional<ProdutoModel> atualizaProduto(Long id, ProdutoModel p) {
        Optional<ProdutoModel> prod = listaProdutoPorId(id);
        if(prod.isEmpty()) {
            return Optional.empty();
        } else {
            prod.get().setNome(p.getNome());
            prod.get().setDescricao(p.getDescricao());
            prod.get().setPreco(p.getPreco());
            prod.get().setStatus(p.getStatus());
            return Optional.of(repository.save(p));
        }
    }
}
