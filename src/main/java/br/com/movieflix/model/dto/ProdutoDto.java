package br.com.movieflix.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoDto {

	private UUID id;
	private String nome;
	private BigDecimal preco;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}
	
	public static Page<ProdutoDto> converter(Page<Produto> produtos){
		return produtos.map(ProdutoDto::new);
	}
}
