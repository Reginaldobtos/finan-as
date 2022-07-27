package br.com.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import br.com.minhasfinancas.model.entity.Usuario;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerifcaraExitenciaDeEmail() {
		
		Usuario usuario = criarUsuario();
		
		entityManager.persist(usuario);
		//repository.save(usuario);
		
		boolean result = repository.existsByEmail("usuario@gmail.com");
		
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		
		//repository.deleteAll();
		
		boolean result = repository.existsByEmail("usuario@gmail.com");
		
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		
		Usuario usuario = criarUsuario();
		
		Usuario usuarioSalvo = repository.save(usuario);
		
		Assertions.assertThat(usuarioSalvo.getId()).isOne();
		
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandNaoExisteNaBase() {
		
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	@Test
	public void deveBuscaUmUsuarioPorEmail() {
		
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nome("usuario")
				.email("usuario@email.com")
				.senha("senha").build();
	}
	
}
