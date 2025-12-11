package backendClubMass.backendClubMass.service;

import backendClubMass.backendClubMass.dto.request.LoginRequest;
import backendClubMass.backendClubMass.dto.request.RegisterRequest;
import backendClubMass.backendClubMass.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
