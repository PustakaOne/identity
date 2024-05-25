package id.ac.ui.cs.pustakaone.identity.factory;

import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.model.User;

public class CustomerFactory implements FactoryUser{
    @Override
    public User createUser(RegisterRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .active(true)
                .email(request.getEmail())
                .noTelp(request.getNoTelp())
                .password(request.getPassword())
                .foto(request.getFoto())
                .jenisKelamin(request.getJenisKelamin())
                .tanggalLahir(request.getTanggalLahir())
                .bio(request.getBio())
                .role("USER")
                .build();
    }
}
