package br.com.devdojo.exam.generator.persistence.repository;

import br.com.devdojo.exam.generator.persistence.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}