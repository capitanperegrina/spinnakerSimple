package com.spinnakersimple.servicios;

import java.util.List;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.vista.UsuarioCompletoView;

// TODO: Auto-generated Javadoc
/**
 * The Interface UsuariosService.
 */
public interface UsuariosService {

	/**
	 * Actualiza perfil.
	 *
	 * @param f
	 *            the f
	 * @param ct
	 *            the ct
	 * @return the usuario POJO
	 */
	UsuarioPOJO actualizaPerfil(final UsuarioPOJO f, final CambioEnTabla ct);

	/**
	 * Alta usuario.
	 *
	 * @param f
	 *            the f
	 * @param ct
	 *            the ct
	 * @return the usuario DTO
	 */
	UsuarioPOJO altaUsuario(final UsuarioPOJO f, final CambioEnTabla ct);

	/**
	 * Guarda usuario.
	 *
	 * @param u
	 *            the u
	 * @param ct
	 *            the ct
	 * @return the usuario POJO
	 */
	UsuarioPOJO guardaUsuario(final UsuarioPOJO u, final CambioEnTabla ct);

	/**
	 * Login.
	 *
	 * @param login
	 *            the login
	 * @param pass
	 *            the pass
	 * @param ct
	 *            the ct
	 * @return the usuario POJO
	 */
	UsuarioPOJO login(String login, String pass, CambioEnTabla ct);

	/**
	 * Reset pass.
	 *
	 * @param id
	 *            the id
	 * @param pw
	 *            the pw
	 * @param phm
	 *            the phm
	 * @param pass
	 *            the pass
	 * @param ct
	 *            the ct
	 * @return the usuario POJO
	 */
	UsuarioPOJO resetPass(String id, String pw, String phm, String pass, CambioEnTabla ct);

	/**
	 * Reset pass form.
	 *
	 * @param id
	 *            the id
	 * @param pw
	 *            the pw
	 * @param phm
	 *            the phm
	 * @param ct
	 *            the ct
	 * @return the usuario POJO
	 */
	UsuarioPOJO resetPassForm(String id, String pw, String phm, CambioEnTabla ct);

	/**
	 * Reset pass request.
	 *
	 * @param mail
	 *            the mail
	 * @param ct
	 *            the ct
	 */
	void resetPassRequest(String mail, CambioEnTabla ct);

//	/**
//	 * Busca usuarios.
//	 *
//	 * @param u
//	 *            the u
//	 * @return the list
//	 */
//	UsuarioCompletoView buscaUsuarioCompleto( Integer idUsuario );

	UsuarioPOJO buscaUsuario( Integer idUsuario );

	/**
	 * Busca usuarios.
	 *
	 * @param u
	 *            the u
	 * @return the list
	 */
	List<UsuarioCompletoView> buscaUsuariosCompleto();

	void eliminaCompletamenteUnUsuario( final Integer idUsuario );

	void eliminaCompletamenteUnUsuario( final String mail );

	boolean existePorMail( String mail );

}
