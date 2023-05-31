package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filial;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilialDto {

	private UUID id;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String nome;
	
	public FilialDto(Filial filial) {
		this.id = filial.getId();
		this.cep = filial.getCep();
		this.endereco = filial.getEndereco();
		this.numero = filial.getNumero();
		this.bairro = filial.getBairro();
		this.cidade = filial.getCidade();
		this.uf = filial.getUf();
		this.nome = filial.getNome();
	}
	
	public static Page<FilialDto> converter(Page<Filial> filiais){
		return filiais.map(FilialDto::new);
	}
}
