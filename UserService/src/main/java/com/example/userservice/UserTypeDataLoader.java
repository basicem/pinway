package com.example.userservice;


import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.models.UserVisibilityType;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.repositories.UserVisibilityTypeRepository;
import com.example.userservice.security.PBKDF2Encoder;
import com.example.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserTypeDataLoader {

    private UserVisibilityTypeRepository userTypeRepository;

    private UserRepository userRepository;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserTypeDataLoader(UserVisibilityTypeRepository userTypeRepository,
                                      UserRepository userRepository,
                                      JdbcTemplate jdbcTemplate){
        this.userTypeRepository = userTypeRepository;
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private  void seedUserVisibilityType() {
        String sql = "SELECT id FROM user_visibility_type LIMIT 1";
        List<UserVisibilityType> ut = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( ut == null || ut.size() == 0){

            UserVisibilityType ut1 = new UserVisibilityType();
            ut1.setType("PUBLIC");

            UserVisibilityType ut2 = new UserVisibilityType();
            ut2.setType("PRIVATE");

            UserVisibilityType ut3 = new UserVisibilityType();
            ut3.setType("FOLLOWED");


            userTypeRepository.save(ut1);
            userTypeRepository.save(ut2);
            userTypeRepository.save(ut3);
        }
        System.out.println(userTypeRepository.count());
    }

    private  void seedUser() {
        String sql = "SELECT id, name, username FROM user LIMIT 1"; //////////

        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( u == null || u.size() == 0){

            Optional<UserVisibilityType> optionalUserVisiblityType = userTypeRepository.findOneByType("PUBLIC");
            UserVisibilityType userVisibilityType = new UserVisibilityType();

            if(optionalUserVisiblityType.isPresent())
                userVisibilityType = optionalUserVisiblityType.get();

            Role role = new Role();
            role.setName("ROLE_USER");
            role.setRoleID(1);
            roleRepository.save(role);

            role = new Role();
            role.setName("ROLE_ADMINISTRATOR");
            role.setRoleID(2);
            roleRepository.save(role);

            var roles = new ArrayList<Role>();
            roles.add(roleRepository.findByName("ROLE_USER"));

            var roles_admin = new ArrayList<Role>();
            roles_admin.add(roleRepository.findByName("ROLE_USER"));
            roles_admin.add(roleRepository.findByName("ROLE_ADMINISTRATOR"));

            User user_ad = new User();
            user_ad.setName("Admin");
            user_ad.setSurname("Admin");
            user_ad.setUsername("Admin");
            user_ad.setEmail("admin@admin.com");
            user_ad.setPassword(passwordEncoder.encode("admin"));
            user_ad.setUserVisibilityType(userVisibilityType);
            user_ad.setRoles(roles_admin);

            User user1 = new User();
            user1.setName("Emina");
            user1.setSurname("Basic");
            user1.setUsername("ebasic");
            user1.setEmail("ebasic@etf.unsa.ba");
            user1.setPassword(passwordEncoder.encode("emina123"));
            user1.setUserVisibilityType(userVisibilityType);
            user1.setRoles(roles);

            optionalUserVisiblityType = userTypeRepository.findOneByType("FOLLOWED");
            userVisibilityType = new UserVisibilityType();

            if(optionalUserVisiblityType.isPresent())
                userVisibilityType = optionalUserVisiblityType.get();

            User user2 = new User();
            user2.setName("Amer");
            user2.setSurname("Hrnjic");
            user2.setUsername("amer");
            user2.setEmail("amer@etf.unsa.ba");
            user2.setPassword(passwordEncoder.encode("amer123"));
            user2.setUserVisibilityType(userVisibilityType);
            user2.setRoles(roles);

            optionalUserVisiblityType = userTypeRepository.findOneByType("PRIVATE");
            userVisibilityType = new UserVisibilityType();

            if(optionalUserVisiblityType.isPresent())
                userVisibilityType = optionalUserVisiblityType.get();

            User user3 = new User();
            user3.setName("Sajra");
            user3.setSurname("Turko");
            user3.setUsername("sturko");
            user3.setEmail("sturko@etf.unsa.ba");
            user3.setPassword(passwordEncoder.encode("sajra123"));
            user3.setUserVisibilityType(userVisibilityType);
            user3.setRoles(roles);


            userRepository.save(user_ad);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);


        }
        System.out.println(userRepository.count());
    }

    @EventListener
    public  void seed(ContextRefreshedEvent event){
        seedUserVisibilityType();
        seedUser();

    }
}

