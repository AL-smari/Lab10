package com.example.lab10.Repository;

import com.example.lab10.Model.JopApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JopApplicationRepository extends JpaRepository<JopApplication,Integer> {
}
