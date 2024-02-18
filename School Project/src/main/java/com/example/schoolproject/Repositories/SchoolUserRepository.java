package com.example.schoolproject.Repositories;

import com.example.schoolproject.Entities.SchoolUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {


}
