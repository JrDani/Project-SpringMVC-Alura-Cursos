package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuario = em.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Este email não esta cadastrado no sistema");
		}
		
		return usuario.get(0);
	}	
}
