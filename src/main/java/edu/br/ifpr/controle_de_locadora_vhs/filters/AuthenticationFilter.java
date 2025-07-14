package edu.br.ifpr.controle_de_locadora_vhs.filters;

import java.io.IOException;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;

import edu.br.ifpr.controle_de_locadora_vhs.entities.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        boolean acessoLiberado = uri.startsWith("/login") ||
                uri.startsWith("/cadastro") ||
                uri.startsWith("/css") ||
                uri.startsWith("/js") ||
                uri.startsWith("/api/salvar") ||
                uri.startsWith("/api/listar") ||
                uri.startsWith("/img");

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");

        if (usuario != null || acessoLiberado) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}