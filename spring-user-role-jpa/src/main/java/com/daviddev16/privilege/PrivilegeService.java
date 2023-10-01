package com.daviddev16.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    private Privilege readPrivilege;
    private Privilege writePrivilege;
    private Privilege adminPrivilege;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    /* criando privilégios padrões */
    public void initializeDefaultPrivileges() {
        readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        adminPrivilege = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");
    }

    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findPrivilegeByName(name);
        return (privilege != null) ? privilege :
                privilegeRepository.save(new Privilege(name));
    }

    public Privilege admin() {
        return adminPrivilege;
    }

    public Privilege read() {
        return readPrivilege;
    }

    public Privilege write() {
        return writePrivilege;
    }

}
