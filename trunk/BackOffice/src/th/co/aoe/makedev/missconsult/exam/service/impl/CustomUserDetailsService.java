package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import th.co.aoe.makedev.missconsult.exam.repository.UserRepository;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

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
	@Autowired
	private MissExamService missExamService;
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
		logger.debug(" xxxxxxxxxxxxxxxxxxxxxxxxxxxx into loadUserByUsername "+username);
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
	       // logger.debug(" xxxxxxxxxxxxxxxxxxxxxxxxxxxx affter uniqueResult "+domainUser);
	     
	        
	        th.co.aoe.makedev.missconsult.exam.domain.UserContact domainUserContact = userRepository.findByUsername(username);
			logger.debug(" xxxxxxxxxxxxxxxxxxxxxxxxxxxx affter loadUserByUsername "+domainUserContact);
			
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
			  boolean isAdmin=false;
			  Long rcId=null;
		if(domainUserContact!=null){
			//logger.debug("  getMcontactName "+domainUser.getMissContact().getMcontactName());
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
		    	   logger.debug("  getMcontactName "+domainContact.getMcontactName());
		    	   if(domainContact.getMcontactIsAdmin()!=null && domainContact.getMcontactIsAdmin().equals("1"))
		    		   isAdmin=true;
		    	   rcId=domainContact.getRcId();
		    	   //domainUserContact.getRoleContact();
		       }
		       tx.commit();
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}finally{
				em.close();
			} 
          
         
			MyUserDetails user=new MyUserDetails(domainUserContact.getUsername(),  
					domainUserContact.getPassword().toLowerCase(),
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					//getAuthorities(domainUser.getRole().getRole()));
					//getAuthorities(domainUserContact.getRole()));
					getAuthorities(getRolesMapping(rcId,isAdmin)));
			MyUser myUser=new MyUser(domainUserContact.getFirstName()+" "+domainUserContact.getLastName());
			th.co.aoe.makedev.missconsult.xstream.MissContact missContact= missExamService.findMissContactByUsername(domainUserContact.getUsername());
			myUser.setMissContact(missContact);
			user.setMyUser(myUser);
		return user;
		}else
			return null;
					
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
//	public  Set<th.co.aoe.makedev.missconsult.xstream.RoleType> getRolesMapping(RoleContact roleContact,boolean isAdmin){
	public  Set<th.co.aoe.makedev.missconsult.xstream.RoleType> getRolesMapping(Long rcId,boolean isAdmin){
		  Set<th.co.aoe.makedev.missconsult.xstream.RoleType> role =new HashSet<th.co.aoe.makedev.missconsult.xstream.RoleType>();
		th.co.aoe.makedev.missconsult.xstream.RoleType defualt= new th.co.aoe.makedev.missconsult.xstream.RoleType();
		   defualt.setRole("ROLE_USER");
		   role.add(defualt); 
		   if(isAdmin){
				th.co.aoe.makedev.missconsult.xstream.RoleType admin= new th.co.aoe.makedev.missconsult.xstream.RoleType();
				admin.setRole("ROLE_ADMIN");
				role.add(admin);
		   }
         
                   
//     	  logger.debug("yyyyyyyyyyyyyyyyyyyyyyy "+roleContact.getRcId());
     //      if(roleContact!=null && roleContact.getRcId()!=null ){
		     if(rcId!=null){
        	  @SuppressWarnings("unchecked")
			List<th.co.aoe.makedev.missconsult.xstream.RoleType> roles= missExamService.listRoleTypeByRcId(rcId);
        	  logger.debug("zzzzzzzzzzzzzzzzzzzzzzzzzzz "+roles);
        	  if(roles!=null && roles.size()>0){
        		  for (th.co.aoe.makedev.missconsult.xstream.RoleType roleType : roles) {
        			  role.add(roleType);
				}
        	  }
        	  logger.debug("xxxxxxxxxxxxxxxxxxxxxxx "+role.size());
        	   /*
        	   EntityManager em = entityManagerFactory.createEntityManager();
    	         try{
                	org.hibernate.ejb.TransactionImpl tx=(org.hibernate.ejb.TransactionImpl)em.getTransaction();
                	tx.begin();
    			  CriteriaBuilder cb = em.getCriteriaBuilder();
    		        CriteriaQuery<th.co.aoe.makedev.missconsult.exam.domain.RoleMapping> query = cb.createQuery(th.co.aoe.makedev.missconsult.exam.domain.RoleMapping.class);
    		       Root<th.co.aoe.makedev.missconsult.exam.domain.RoleMapping> mapping = query.from(th.co.aoe.makedev.missconsult.exam.domain.RoleMapping.class);

    		       th.co.aoe.makedev.missconsult.exam.domain.RoleMappingPK pk =new th.co.aoe.makedev.missconsult.exam.domain.RoleMappingPK();
    		       pk.setRcId(roleContact.getRcId());
    		       
    		       query.where(cb.equal(mapping.get("id").as(th.co.aoe.makedev.missconsult.exam.domain.RoleMappingPK.class),
    		    		   pk));
    		    //    th.co.aoe.makedev.missconsult.exam.domain.RoleMapping domainRoleMapping =null;
    		       List<th.co.aoe.makedev.missconsult.exam.domain.RoleMapping> domainRoleMappings= em.createQuery(query).getResultList();
    		       if(domainRoleMappings!=null && domainRoleMappings.size()>0){ 
    		    	   for (th.co.aoe.makedev.missconsult.exam.domain.RoleMapping roleMapping : domainRoleMappings) {
    		    		   //roleMapping.getId().
    		    		   CriteriaQuery<th.co.aoe.makedev.missconsult.exam.domain.RoleType> query2 = cb.createQuery(th.co.aoe.makedev.missconsult.exam.domain.RoleType.class);
    	    		       Root<th.co.aoe.makedev.missconsult.exam.domain.RoleType> roletype = query.from(th.co.aoe.makedev.missconsult.exam.domain.RoleType.class);

    	    		       query2.where(cb.equal(roletype.get("rtId").as(Long.class),
    	    		    		   roleMapping.getId().getRtId()));
    	    		       List<th.co.aoe.makedev.missconsult.exam.domain.RoleType> domainroletypes= em.createQuery(query2).getResultList();
    	    		       for (RoleType roleType2 : domainroletypes) {
    	    		    	   logger.debug(" xxxxxxxx ROLE="+roleType2.getRole());
						}
					}
    		       }
    		       tx.commit();
                }catch (Exception e) {
    				// TODO: handle exception
                	e.printStackTrace();
    			}finally{
    				em.close();
    			} 
           */}
		return role;
	}
	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	/*public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}*/
	public Collection<? extends GrantedAuthority> getAuthorities(Set<th.co.aoe.makedev.missconsult.xstream.RoleType> role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
/*	public List<String> getRoles(Set<Role> role) {
		List<String> roles = new ArrayList<String>();
		if(role!=null && role.size()>0)
		for (Role key : role) {
			roles.add(key.getRole());
		}
		return roles;
	}*/
	public List<String> getRoles(Set<th.co.aoe.makedev.missconsult.xstream.RoleType> role) {
		List<String> roles = new ArrayList<String>();
		if(role!=null && role.size()>0)
		for (th.co.aoe.makedev.missconsult.xstream.RoleType key : role) {
			roles.add(key.getRole());
		}
		return roles;
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	/*public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}*/
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
