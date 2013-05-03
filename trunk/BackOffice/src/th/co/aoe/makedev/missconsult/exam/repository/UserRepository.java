package th.co.aoe.makedev.missconsult.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import th.co.aoe.makedev.missconsult.exam.domain.UserContact;

//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository extends JpaRepository<UserContact, Long> {
	
	//User findByUsername(String username);
	UserContact findByUsername(String username);
}
