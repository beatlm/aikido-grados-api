package com.aikido.grados.aikidogrados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@WebFilter(urlPatterns = "/api/*")
public class JwtFilter implements Filter {
 
  @Value("${jwt.secret}")
  private String secret;
 
  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
 
  }
 
  @Override
  public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) req;
    final HttpServletResponse response = (HttpServletResponse) res;
    final String authHeader = request.getHeader("authorization");
 
    if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
      chain.doFilter(req, res);
    } else {
 
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
 
      final String token = authHeader.substring(7);
 
      try {
        final Claims claims = Jwts.parser()
            .setSigningKey(TextCodec.BASE64.encode(secret))
            .parseClaimsJws(token)
            .getBody();
        request.setAttribute("claims", claims);
      } catch (final JwtException e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
 
      chain.doFilter(req, res);
    }
  }
 
  @Override
  public void destroy() {
 
  }
}