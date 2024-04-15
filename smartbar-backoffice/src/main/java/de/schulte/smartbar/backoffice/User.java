package de.schulte.smartbar.backoffice;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@UserDefinition
@Table(name = "sbo_user")
public class User extends BaseEntity {

    @Username
    public String username;

    @Password()
    public String password;

    @Roles
    public String roles;

}
