package br.com.financas.financas.service;



import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.minhasfinancas.exception.RegraNegocioException;
import br.com.minhasfinancas.model.entity.Usuario;
import br.com.minhasfinancas.model.repository.UsuarioRepository;
import br.com.minhasfinancas.service.UsuarioService;
import br.com.minhasfinancas.serviceImpl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Before
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveValidarEmail() {
		
		//repository.deleteAll();
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		service.validarEmail("email@gmail.com");
	
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		
		// RegraNegocioException.class
		Usuario usuario = Usuario.builder().nome("usuario").email("email@yahoo.com").build();
		repository.save(usuario);
		
		service.validarEmail("email@yahoo.com");
	}
	
}
