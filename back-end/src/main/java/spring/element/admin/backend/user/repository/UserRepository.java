package spring.element.admin.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.element.admin.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
