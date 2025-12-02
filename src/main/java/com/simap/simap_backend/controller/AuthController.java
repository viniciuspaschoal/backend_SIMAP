package com.simap.simap_backend.controller;

import com.google.firebase.auth.FirebaseToken;
import com.simap.simap_backend.model.Users;
import com.simap.simap_backend.repository.UsersRepository;
import com.simap.simap_backend.service.FirebaseAuthService;
import com.simap.simap_backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private FirebaseAuthService firebaseAuthService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody TokenDTO dto) {

        FirebaseToken token = firebaseAuthService.validateToken(dto.getToken());

        // Dados vindos do Firebase
        String email = token.getEmail();
        String nome = token.getName();
        String fotoUrl = token.getPicture();

        // Cria / atualiza usuário no banco
        Users user = usersRepository.findByEmail(email)
                .orElseGet(() -> new Users(nome, email, fotoUrl));

        user.setNome(nome);
        user.setFotoUrl(fotoUrl);
        usersRepository.save(user);

        // Gera seu JWT interno
        return jwtService.generateToken(user.getEmail());
    }

    public static class TokenDTO {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
