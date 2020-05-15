package com.techeasy.contas.receber.config;

import com.techeasy.contas.receber.domain.usuarios.model.Cargo;
import com.techeasy.contas.receber.domain.usuarios.model.Usuario;
import com.techeasy.contas.receber.infra.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String login)	throws UsernameNotFoundException, DataAccessException {
        List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
        Usuario user = userRepo.findByLogin(login);
        checkGrantAuthorities(user, listGrantAuthority);
        UserDetails userDetails = validateUser(login, listGrantAuthority, user);
        return userDetails;
    }

    private void checkGrantAuthorities(Usuario user, List<GrantedAuthority> listGrantAuthority) {
        if (user != null && user.getCargo() != null) {
            Cargo cargo = user.getCargo();
            String role = "ROLE_ADMIN";

            listGrantAuthority.add((GrantedAuthority) () -> role);
        }
    }

    private UserDetails validateUser(String email, List<GrantedAuthority> listGrantAuthority, Usuario user) {
        UserDetails userDetails = null;
        if (user != null) {
            userDetails = new User(email,
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    listGrantAuthority);
        }
        return userDetails;
    }
}
