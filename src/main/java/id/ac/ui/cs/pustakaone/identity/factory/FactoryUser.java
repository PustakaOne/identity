package id.ac.ui.cs.pustakaone.identity.factory;

import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.model.User;

public interface FactoryUser {
    User createUser(RegisterRequest request);
}
