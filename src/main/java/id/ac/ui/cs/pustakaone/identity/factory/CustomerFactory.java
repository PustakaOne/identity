package id.ac.ui.cs.pustakaone.identity.factory;

import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.enums.EnumJenisKelamin;
import id.ac.ui.cs.pustakaone.identity.enums.EnumRole;
import id.ac.ui.cs.pustakaone.identity.model.User;

public class CustomerFactory implements FactoryUser{
    @Override
    public User createUser(RegisterRequest request) {
        EnumJenisKelamin jenisKelamin = EnumJenisKelamin.valueOf(request.getJenisKelamin().toUpperCase());

        return User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .active(true)
                .email(request.getEmail())
                .noTelp(request.getNoTelp())
                .password(request.getPassword())
                .foto(request.getFoto())
                .jenisKelamin(jenisKelamin.getValue())
                .tanggalLahir(request.getTanggalLahir())
                .bio(request.getBio())
                .role(EnumRole.USER.getValue())
                .build();
    }
}
