package br.com.minhasfinancas.service;

import java.util.List;

import br.com.minhasfinancas.model.entity.Lancamento;
import br.com.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamento);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lancamento);
}
