package com.device.management.controller;



import com.device.management.dto.ApiResponse;
import com.device.management.dto.DeviceDTO;
import com.device.management.dto.DeviceFullDTO;
import com.device.management.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test(){

        return ResponseEntity.ok(ApiResponse.success("getテスト成功"));
    }


    @PostMapping("/insertDevice")
    public ResponseEntity<ApiResponse<DeviceFullDTO>> insertDevice(@RequestBody DeviceFullDTO deviceFullDTO){ //传入creator？

        DeviceFullDTO deviceFullDTO1 = deviceService.insertDevice(deviceFullDTO); //設備の挿入に成功しました

        return ResponseEntity.ok(ApiResponse.success(deviceFullDTO1));
    }



//    @PostMapping("/insertDevice")
//    public ResponseEntity<ApiResponse<DeviceDTO>> insertDevice(@RequestBody DeviceDTO deviceDTO){ //传入creator？
//
//        DeviceDTO deviceDTO1 = deviceService.insertDevice(deviceDTO); //設備の挿入に成功しました
//
//        return ResponseEntity.ok(ApiResponse.success(deviceDTO1));
//    }

    @PutMapping("/updateDevice/{id}")
    public ResponseEntity<ApiResponse<DeviceFullDTO>> updateDevice(@PathVariable String id, @RequestBody DeviceFullDTO deviceFullDTO){

        DeviceFullDTO deviceFullDTO1 = deviceService.updateDeviceById(id, deviceFullDTO); //設備の挿入に成功しました

        return ResponseEntity.ok(ApiResponse.success(deviceFullDTO1));

    }

//    @PutMapping("/updateDevice/{id}")
//    public ResponseEntity<ApiResponse<DeviceDTO>> updateDevice(@PathVariable String id, @RequestBody DeviceDTO deviceDTO){
//
//        DeviceDTO deviceDTO1 = deviceService.updateDeviceById(id, deviceDTO); //設備の挿入に成功しました
//
//        return ResponseEntity.ok(ApiResponse.success(deviceDTO1));
//
//    }

}
