package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FuncionarioDto {

	private UUID id;
	private String nome;
	private String email;
	private boolean gerent;
	private Filial filial;
	
	public FuncionarioDto(Funcionario func) {
		this.id = func.getId();
		this.nome = func.getNome();
		this.email = func.getEmail();
		this.gerent = func.isGerent();
		this.filial = func.getFilialId();
	}
	
	public static Page<FuncionarioDto> converter(Page<Funcionario> funcs){
		return funcs.map(FuncionarioDto::new);
	}
}