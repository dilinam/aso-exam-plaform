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
                    "Dilina", "Madhushan", "961111111v", "abc, abc","this is img", "1234567890",
                    "dilina@gmail.com", 833736600L, true, false, false, null, null));
            users = userRepository.saveAll(users);

            // save tenants
            tenants.add(new Tenant(null, "TEST", "Test Description", true, false, null, 0L, null, 0L));
            tenants = tenantRepository.saveAll(tenants);

            // save roles
            roles.add(new Role(1L, "TENANT_ADMIN", null));
            roles.add(new Role(2L, "EXAMINER", null));
            roles.add(new Role(3L, "CANDIDATE", null));
            roles = roleRepository.saveAll(roles);

            // save tenant users
            tenantUsers.add(new TenantUser(null, tenants.get(0), users.get(0), roles.get(0), true, false));
            tenantUsers = tenantUserRepository.saveAll(tenantUsers);

            // save modules
            Module userModule = new Module(null, "USER");
            Module tenantModule = new Module(null, "TENANT");
            Module examModule = new Module(null, "EXAM");
            Module courseModule = new Module(null, "COURSE");
            modules.add(userModule);
            modules.add(tenantModule);
            modules.add(examModule);
            modules.add(courseModule);
            modules = moduleRepository.saveAll(modules);

            // save actions
            actions.add(new Action(null, "VIEW"));
            actions.add(new Action(null, "ADD"));
            actions.add(new Action(null, "UPDATE"));
            actions.add(new Action(null, "DELETE"));
            actions = actionRepository.saveAll(actions);

            // save module actions
            for (Action action : actions) {
                switch (action.getActionName()) {
                    case "ADD":
                        moduleActions.add(new ModuleAction(null, "/api/users", "POST",
                                action, userModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/users/candidate", "POST",
                                action, userModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/tenant", "POST",
                                action, tenantModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/exam", "POST",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/Question", "POST",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/course", "POST",
                                action, courseModule, roles));
                        break;
                    case "UPDATE":
                        moduleActions.add(new ModuleAction(null, "/api/users", "PUT",
                                action, userModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/tenant", "PUT",
                                action, tenantModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/exam", "PUT",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/Question", "PUT",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/course", "PUT",
                                action, courseModule, roles));
                        break;
                    case "DELETE":
                        moduleActions.add(new ModuleAction(null, "/api/users", "DELETE",
                                action, userModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/tenant", "DELETE",
                                action, tenantModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/exam", "DELETE",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/Question", "DELETE",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/course", "DELETE",
                                action, courseModule, roles));
                        break;
                    case "VIEW":
                    default:
                        moduleActions.add(new ModuleAction(null, "/api/users", "GET",
                                action, userModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/tenant", "GET",
                                action, tenantModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/exam", "GET",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/Question", "GET",
                                action, examModule, roles));
                        moduleActions.add(new ModuleAction(null, "/api/course", "GET",
                                action, courseModule, roles));
                        break;
                }
            }
            moduleActions = moduleActionRepository.saveAll(moduleActions);
            for (Role role : roles) {
                role.setModuleActions(moduleActions);
            }
            roleRepository.saveAll(roles);

        }

    }
}
