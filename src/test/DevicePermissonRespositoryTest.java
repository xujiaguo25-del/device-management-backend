import com.device.DeviceManagementApplication;
import com.device.management.repository.DevicePermissionRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DeviceManagementApplication.class)
public class DevicePermissonRespositoryTest {

    @Resource
    DevicePermissionRepository devicePermissonRespository;
    @Test
    public void testFindByDeviceId() {
        System.out.println(devicePermissonRespository.findAllDevicePermissionExcel());
    }

}
