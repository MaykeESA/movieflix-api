package br.com.movieflix.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.model.Funcionario;
import br.com.movieflix.model.dto.FuncionarioDto;
import br.com.movieflix.model.form.FuncionarioForm;
import br.com.movieflix.model.form.att.FuncionarioAttForm;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcService;

	@Autowired
	private FilialRepository filialRep;

	@GetMapping
	public Page<FuncionarioDto> listarTodos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.funcService.pageFuncionario(paginacao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDto> listarUnico(@PathVariable UUID id) {
		if (this.funcService.isFuncionarioPresent(id)) {
			Funcionario func = this.funcService.getFuncionarioById(id);
			return ResponseEntity.ok(new FuncionarioDto(func));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<FuncionarioDto> cadastrar(@RequestBody @Valid FuncionarioForm funcForm, UriComponentsBuilder uriBuilder) {
		Funcionario func = funcForm.converter(this.filialRep);
		URI uri = this.funcService.cadastrar(func, uriBuilder);
		return ResponseEntity.created(uri).body(new FuncionarioDto(func));
	}
	
	@PostMapping("/{id}/gerente")
	public ResponseEntity<FuncionarioDto> cadastrarGerente(@PathVariable UUID id){
		if(this.funcService.isFuncionarioPresent(id)) {
			this.funcService.cadastrarGerente(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDto> atualizar(@PathVariable UUID id, @RequestBody @Valid FuncionarioAttForm funcAttForm) {
		if (this.funcService.isFuncionarioPresent(id)) {
			Funcionario func = this.funcService.atualizar(id, funcAttForm);
			return ResponseEntity.ok(new FuncionarioDto(func));
		}

		return ResponseEntity.notFound().build();
	} 

	@DeleteMapping("/{id}")
	public ResponseEntity<FuncionarioDto> remover(@PathVariable UUID id) {
		if (this.funcService.isFuncionarioPresent(id)) {
			this.funcService.deletarFuncionarioById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
