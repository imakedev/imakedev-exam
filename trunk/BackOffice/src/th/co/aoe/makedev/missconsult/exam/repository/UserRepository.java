package th.co.aoe.makedev.missconsult.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.co.aoe.makedev.missconsult.exam.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
