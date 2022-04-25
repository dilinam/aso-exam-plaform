package com.aso.examplatform.util;

import com.aso.examplatform.model.*;
import com.aso.examplatform.model.Module;
import com.aso.examplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitialDataGeneration implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    private final TenantRepository tenantRepository;
    private final ActionRepository actionRepository;
    private final TenantUserRepository tenantUserRepository;
    private final ModuleActionRepository moduleActionRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        generateModulesRoles();
    }

    private void generateModulesRoles(){
        List<Role> roles = new ArrayList<>();
        List<Module> modules = new ArrayList<>();
        List<Action> actions = new ArrayList<>();
        List<Tenant> tenants = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<ModuleAction> moduleActions = new ArrayList<>();
        List<TenantUser> tenantUsers = new ArrayList<>();

        if(userRepository.count() == 0){

            // save user
            users.add(new User(null, "dilina", BCrypt.hashpw("1234", BCrypt.gensalt()),
                    "Dilina", "Madhushan", "961111111v", "abc, abc", "1234567890",
                    "dilina@gmail.com", 833736600L, true, false, false));
            users = userRepository.saveAll(users);

            // save tenants
            tenants.add(new Tenant(null, "TEST", "Test Description", true, false, null, null));
            tenants = tenantRepository.saveAll(tenants);

            // save roles
            roles.add(new Role(null, "TENANT_ADMIN", null));
            roles.add(new Role(null, "INVIGILATOR", null));
            roles.add(new Role(null, "CANDIDATE", null));
            roles = roleRepository.saveAll(roles);

            // save tenant users
            tenantUsers.add(new TenantUser(null, tenants.get(0), users.get(0), roles.get(0), true, false));
            tenantUsers = tenantUserRepository.saveAll(tenantUsers);

            // save modules
            modules.add(new Module(null, "USER"));
            modules.add(new Module(null, "TENANT"));
            modules.add(new Module(null, "EXAM"));
            modules = moduleRepository.saveAll(modules);

            // save actions
            actions.add(new Action(null, "VIEW"));
            actions.add(new Action(null, "ADD"));
            actions.add(new Action(null, "UPDATE"));
            actions.add(new Action(null, "DELETE"));
            actions = actionRepository.saveAll(actions);

            // save module actions
            Module userModule = null;
            for (int i = 0; i < modules.size(); i++){
                if(modules.get(i).getModuleName().equals("USER")){
                    userModule = modules.get(i);
                }
            }
            for(int i=0; i< actions.size(); i++){
                if(actions.get(i).getActionName().equals("VIEW")){
                    moduleActions.add(new ModuleAction(null, "/api/users", "GET",
                            actions.get(i),userModule, roles));
                }else if(actions.get(i).getActionName().equals("ADD")){
                    moduleActions.add(new ModuleAction(null, "/api/users", "POST",
                            actions.get(i),userModule, roles));
                }else if(actions.get(i).getActionName().equals("UPDATE")){
                    moduleActions.add(new ModuleAction(null, "/api/users", "PUT",
                            actions.get(i),userModule, roles));
                }else if(actions.get(i).getActionName().equals("DELETE")){
                    moduleActions.add(new ModuleAction(null, "/api/users", "DELETE",
                            actions.get(i),userModule, roles));
                }else{
                    if(actions.get(i).getActionName().equals("VIEW")){
                        moduleActions.add(new ModuleAction(null, "/api/users", "GET",
                                actions.get(i),userModule, roles));
                    }
                }
            }
            moduleActions = moduleActionRepository.saveAll(moduleActions);
            for (int i = 0; i < roles.size(); i++){
                roles.get(i).setModuleActions(moduleActions);
            }
            roleRepository.saveAll(roles);

        }

    }
}
