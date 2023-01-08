package mikroservisneproj2.service1.boostrap;

import mikroservisneproj2.service1.domain.Admin;
import mikroservisneproj2.service1.domain.UserInfo;
import mikroservisneproj2.service1.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    private final AdminRepository adminRepository;

    @Autowired
    public BootStrapData(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Admin admin = new Admin();
        UserInfo adminInfo = new UserInfo();
        adminInfo.setName("Admin");
        adminInfo.setEmail("admin@gmail.com");
        adminInfo.setPassword("123");
        adminInfo.setSurname("Adminic");
        adminInfo.setPhone("064051212");
        adminInfo.setUsername("Adminnn");
        adminInfo.setBirthDate(1420579840);
        admin.setUserInfo(adminInfo);
        this.adminRepository.save(admin);
    }
}
