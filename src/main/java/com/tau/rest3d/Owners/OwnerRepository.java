package com.tau.rest3d.Owners;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {


    Optional<Owner> findOwnerByEmail(String email);

    Optional<Owner> findOwnerByPassword(String password);

    Optional<Owner> findOwnerByUserName(String username);

    Optional<Owner> findOwnerByDateOfSignUp(LocalDate dateOfSignUp);

}
