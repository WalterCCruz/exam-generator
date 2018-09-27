package br.com.devdojo.exam.generator.endpoint.v1.persistence.repository;

import br.com.devdojo.exam.generator.endpoint.v1.persistence.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUserName(String username);
}
