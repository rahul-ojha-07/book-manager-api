package in.rahulojha.bookmanagerapi.auth.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private boolean isAdmin;
}
