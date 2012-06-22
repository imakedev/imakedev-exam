package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.domain.MyUser;
import th.co.aoe.makedev.missconsult.exam.domain.MyUserDetails;
import th.co.aoe.makedev.missconsult.exam.domain.Role;
import th.co.aoe.makedev.missconsult.exam.repository.UserRepository;

/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from a JPA repository
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	@Autowired
	private UserRepository userRepository;
	/*@Autowired
	private MissExamService missExamService;*/
	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceUnit(unitName = "hibernatePersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

	/**
	 * Returns a populated {@link UserDetails} object. 
	 * The username is first retrieved from the database and then mapped to 
	 * a {@link UserDetails} object.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.error(" xxxxxxxxxxxxxxxxxxxxxxxxxxxx into loadUserByUsername "+username);
		try {
		
			 /*  CriteriaBuilder cb = em.getCriteriaBuilder();
		        CriteriaQuery<th.co.aoe.makedev.missconsult.exam.domain.User> query = cb.createQuery(th.co.aoe.makedev.missconsult.exam.domain.User.class);
		       Root<th.co.aoe.makedev.missconsult.exam.domain.User> account = query.from(th.co.aoe.makedev.missconsult.exam.domain.User.class);

		        query.where(cb.equal(account.get("username").as(String.class),
		        		username));
		        th.co.aoe.makedev.missconsult.exam.domain.User domainUser =null;
		       List<th.co.aoe.makedev.missconsult.exam.domain.User> domainUsers= em.createQuery(query).getResultList();
		       if(domainUsers!=null && domainUsers.size()>0)
		    	   domainUser=domainUsers.get(0);*/
	       // logger.error(" xxxxxxxxxxxxxxxxxxxxxxxxxxxx affter uniqueResult "+domainUser);
	     
	        
	        th.co.aoe.makedev.missconsult.exam.domain.User domainUser = userRepository.findByUsername(username);
			logger.error(" xxxxxxxxxxxxxxxxxxxxxxxxxxxx affter loadUserByUsername "+domainUser);
			
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
		 
			/*return new User(
					//domainUser.getUsername(), 
					domainUser.getFirstName(),
					domainUser.getPassword().toLowerCase(),
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					getAuthorities(domainUser.getRole().getRole()));*/
		if(domainUser!=null){
			//logger.error("  getMcontactName "+domainUser.getMissContact().getMcontactName());
			EntityManager em = entityManagerFactory.createEntityManager();
	       /* try {
	              entityManager.createQuery("from TestEntity").getResultList();
	        } finally {
	            entityManager.close();
	        }*/
            try{
            	org.hibernate.ejb.TransactionImpl tx=(org.hibernate.ejb.TransactionImpl)em.getTransaction();
            	tx.begin();
			  CriteriaBuilder cb = em.getCriteriaBuilder();
		        CriteriaQuery<th.co.aoe.makedev.missconsult.exam.domain.MissContact> query = cb.createQuery(th.co.aoe.makedev.missconsult.exam.domain.MissContact.class);
		       Root<th.co.aoe.makedev.missconsult.exam.domain.MissContact> contact = query.from(th.co.aoe.makedev.missconsult.exam.domain.MissContact.class);

		        query.where(cb.equal(contact.get("mcontactUsername").as(String.class),
		        		username));
		        th.co.aoe.makedev.missconsult.exam.domain.MissContact domainContact =null;
		       List<th.co.aoe.makedev.missconsult.exam.domain.MissContact> domainContacts= em.createQuery(query).getResultList();
		       if(domainContacts!=null && domainContacts.size()>0){
		    	   domainContact=domainContacts.get(0);
		    	   logger.error("  getMcontactName "+domainContact.getMcontactName());
		       }
		       tx.commit();
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}finally{
				em.close();
			} 
			MyUserDetails user=new MyUserDetails(domainUser.getUsername(),  
					domainUser.getPassword().toLowerCase(),
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					//getAuthorities(domainUser.getRole().getRole()));
					getAuthorities(domainUser.getRole()));
			MyUser myUser=new MyUser(domainUser.getFirstName()+" "+domainUser.getLastName());
			user.setMyUser(myUser);
		return user;
		}else
			return null;
					
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(Set<Role> role) {
		List<String> roles = new ArrayList<String>();
		if(role!=null && role.size()>0)
		for (Role key : role) {
			roles.add(key.getRole());
		}
		/*if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
			
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}*/
		
		return roles;
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
