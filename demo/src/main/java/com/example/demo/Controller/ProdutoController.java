package com.example.demo.Controller;

import com.example.demo.Model.ProdutoModel;
import com.example.demo.Service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/produto")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService ser) {
        service = ser;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> adicionaProduto(@RequestBody ProdutoModel p) {
        ProdutoModel prod = service.adicionaProduto(p);
        return ResponseEntity.status(201).body(prod);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listaProdutos() {
        List<ProdutoModel> p = service.listaProdutos();
        if(p.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProdutoModel>> listaProdutosPorId(@PathVariable Long id) {
        Optional<ProdutoModel> p = service.listaProdutoPorId(id);
        if(p.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaProduto(@PathVariable Long id) {
        Optional<ProdutoModel> p = service.listaProdutoPorId(id);
        if(p.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            service.deletaProdutoPorId(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizaProduto(@PathVariable Long id, @RequestBody ProdutoModel p) {
        Optional<ProdutoModel> prod = service.listaProdutoPorId(id);
        if(prod.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(p);
        }
    }



}
