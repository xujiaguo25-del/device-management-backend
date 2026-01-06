package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.DevicePermissionExcelVo;
import com.device.management.dto.PermissionsDTO;
import com.device.management.entity.DeviceInfo;
import com.device.management.entity.DevicePermission;
import com.device.management.entity.User;
import com.device.management.service.DevicePermissionExcelService;
import com.device.management.service.DevicePermissionService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*功能	方法	端点	参数/请求体	说明
列表	GET	/permissions	?page=1&size=10&deviceId=xxx&userId=xxx	-
详情	GET	/permissions/{id}	-	-
新增	POST	/permissions	Permission 对象	-
编辑	PUT	/permissions/{id}	Permission 对象	-
导出	GET	/permissions/export	-	返回 Excel 文件
删除 DELETE	/permissions/{id}	-	-
*/

@RestController
@RequestMapping("/permissions")
public class DevicePermissionController {
    @Resource
    DevicePermissionService devicePermissionService;
    @Resource
    private DevicePermissionExcelService devicePermissionExcelService;

    //権限一覧を照会します
    @GetMapping
    public ApiResponse<List<PermissionsDTO>> getPermissions(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "userInfo", required = false) User user,
            @RequestParam(value = "deviceInfo", required = false) DeviceInfo deviceInfo,
            @RequestParam(value = "permissionInfo", required = false) String permissionInfo
    )
    {
       return devicePermissionService.getPermissions(page,size,user,deviceInfo,permissionInfo);
    }

    //権限を追加します
    @PostMapping
    public  ApiResponse<PermissionsDTO> addPermissions(
            @RequestBody PermissionsDTO devicePermission
    ){
        return devicePermissionService.addPermissions(devicePermission);
    }

    //権限詳細
    @GetMapping(value = "/{id}")
    public ApiResponse<PermissionsDTO> getPermissions() {
        return null;
    }

    //権限を更新します
    @PutMapping
    public ApiResponse<PermissionsDTO> updatePermissions(
            @RequestBody PermissionsDTO permissionsDTO
    ){
        return devicePermissionService.updatePermissions(permissionsDTO);
    }

    //権限を削除します
    @DeleteMapping(value = "/{id}")
    public ApiResponse<Void> deletePermissions(
            @PathVariable("id") String id
    ){
        return devicePermissionService.deletePermissions(id);
    }

    //権限をexcelファイル形式でエクスポートします
    @GetMapping(value = "/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 这里应该从数据库获取数据
        List<DevicePermissionExcelVo> dataList = devicePermissionExcelService.getDataFromDatabase();
        // 导出Excel
        devicePermissionExcelService.exportDevicePermissionList(dataList, response);
    }





    /**
     * 模拟从数据库获取数据
     */
//    private List<DevicePermissionExcelVo> getDataFromDatabase() {
//        List<DevicePermissionExcelVo> list = new ArrayList<>();
//
//        // 示例数据
//        DevicePermissionExcelVo vo1 = new DevicePermissionExcelVo();
//        vo1.setNumber(1);
//        vo1.setDeviceNumber("hyron-220914 pc-dc-049\nhyron-220914 pc-dc-049(显示器)");
//        vo1.setComputerName("DA04-liuwenfeng");
//        vo1.setIpAddress("10.6.1.99\n10.6.3.221");
//        vo1.setEmployeeId("JS0020");
//        vo1.setEmployeeName("刘文丰");
//        vo1.setDepartmentCode("DA04");
//        vo1.setLoginUsername("lfeng");
//        vo1.setSmartitStatus("本地");
//        vo1.setUsbStatus("关闭");
//        vo1.setConnectionStatus("自动");
//
//        DevicePermissionExcelVo vo2 = new DevicePermissionExcelVo();
//        vo2.setNumber(4);
//        vo2.setDeviceNumber("hyron-220914 pc-dc-053\nHYRON-241118 Minitor-076(显示器)");
//        vo2.setComputerName("DA04-LUNA-PC09");
//        vo2.setIpAddress("10.6.1.36\n10.6.1.244\n10.6.3.232");
//        vo2.setEmployeeId("JS0054");
//        vo2.setEmployeeName("朱德涛");
//        vo2.setDepartmentCode("DA04");
//        vo2.setLoginUsername("zhudt");
//        vo2.setSmartitStatus("本地");
//        vo2.setUsbOpenReason("项目需要");
//        vo2.setConnectionStatus("自动");
//        // 设置日期（示例）
//        // vo2.setUseDeadline(new SimpleDateFormat("yyyy-MM-dd").parse("2025-12-31"));
//
//        list.add(vo1);
//        list.add(vo2);
//
//        return list;
//    }
}
