package com.capitanperegrina.usuarios.servicios;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;

public interface UsuarioSrv 
{
	public UsuarioPOJO creaUsuario( UsuarioPOJO u );
	
	public UsuarioPOJO login( String login, String pass, CambioEnTabla ct ) throws ServicioException;

	public UsuarioPOJO buscaPorMail( String mail );

	public boolean existePeticionResetPassActiva( Integer idUsuario, String codigoRecuperacion );

	public UsuarioPOJO recoverPassInit( String mail, CambioEnTabla ct );	

	public UsuarioPOJO recoverPassExec( Integer idUsuario, String codigoRecuperacion, String newPass, CambioEnTabla ct ) throws ServicioException;

	public UsuarioPOJO actualiza( UsuarioPOJO usuario, CambioEnTabla ct );
}
