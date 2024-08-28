package baheanet.schoolApi.controllers;

import baheanet.schoolApi.domain.Usuario.DadosAutenticacao;
import baheanet.schoolApi.domain.Usuario.Usuario;
import baheanet.schoolApi.domain.Usuario.UsuarioRepository;
import baheanet.schoolApi.infra.DadosTokenJWT;
import baheanet.schoolApi.infra.excepitions.ValidaRegraDeNegocios;
import baheanet.schoolApi.infra.security.TokenService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    @PostMapping("/register")
    public void registerUser(@RequestBody Usuario dados) {
        // Verifica se o usuário já existe
        if (usuarioRepository.findByLogin(dados.getUsername()) !=  null) {
            throw new ValidaRegraDeNegocios("Usuário já existe.");
        }

        // Criptografa a senha
        String encodedPassword = bCryptPasswordEncoder.encode(dados.getPassword());

        Usuario newUser = new Usuario(null, dados.getLogin(),encodedPassword);
        usuarioRepository.save(newUser);
    }
}


