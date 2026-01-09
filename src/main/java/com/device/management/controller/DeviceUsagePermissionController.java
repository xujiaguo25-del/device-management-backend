package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.DeviceUsagePermissionDTO;
import com.device.management.entity.DeviceUsagePermission;
import com.device.management.service.DeviceUsagePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
public class DeviceUsagePermissionController {
    @Autowired
    private DeviceUsagePermissionService permissionService;

    /**
     * 権限IDに基づいて詳細情報を取得
     *  リクエスト方式：GET
     *  リクエストパス：/permissions/{permissionId}
     *  1. パスパラメータ permissionId を受け取る
     *  2. サービス層の findPermissionDetail メソッドを呼び出す
     *  3. サービス層がデータベースからデータを取得し、DTO に変換する
     *  4. APIレスポンスとして統一された ApiResponse にパッケージ化して返す
     */
    @GetMapping("/{permissionId}")
    public ApiResponse<DeviceUsagePermissionDTO> getPermissionDetail(@PathVariable String permissionId)
    {
        //サービス層を呼び出してデータを取得する
        DeviceUsagePermissionDTO dto = permissionService.findPermissionDetail(permissionId);
        //統一されたレスポンス形式をカプセル化して返す
        return ApiResponse.success("検索に成功しました", dto);
    }

    /**
     * 権限情報の更新
     *  リクエスト方式：PUT
     *  リクエストパス：/permissions/{permissionId}
     *  1. パスパラメータ permissionId を受け取る
     *  2. 11 個の任意のクエリパラメータを受け取る
     *  3. サービス層の updatePermissionByFields メソッドを呼び出す
     *  4. サービス層は値があるフィールドのみを更新する
     *  5. 操作結果を返す
     * @param permissionId
     * @param smartitStatusId
     * @param noSmartitReason
     * @param usbStatusId
     * @param usbReason
     * @param usbExpireDate
     * @param antivirusStatusId
     * @param noSymantecReason
     * @param domainStatusId
     * @param domainGroup
     * @param noDomainReason
     * @param remark
     * @return
     */
    @PutMapping("/{permissionId}")
    public ApiResponse<Void> updatePermission(
            //更新する権限ID
            @PathVariable String permissionId,

            @RequestParam(required = false) String smartitStatusId,        //  smartlt_status_id
            @RequestParam(required = false) String noSmartitReason,        //  no_smartlt_reason
            @RequestParam(required = false) String usbStatusId,            //  usb_status_id
            @RequestParam(required = false) String usbReason,              //  usb_reason
            @RequestParam(required = false) LocalDate usbExpireDate,       //  usb_expire_date
            @RequestParam(required = false) String antivirusStatusId,      //  antivirus_status_id
            @RequestParam(required = false) String noSymantecReason,       //  no_symantec_reason
            @RequestParam(required = false) String domainStatusId,         //  domain_status_id
            @RequestParam(required = false) String domainGroup,            //  domain_group
            @RequestParam(required = false) String noDomainReason,         //  no_domain_reason
            @RequestParam(required = false) String remark                 //  remark
     ) {
        //サービス層のメソッドを呼び出し、すべてのパラメータを渡す
        permissionService.updatePermissionByFields(permissionId,
                smartitStatusId, noSmartitReason,
                usbStatusId, usbReason, usbExpireDate,
                antivirusStatusId, noSymantecReason,
                domainStatusId, domainGroup, noDomainReason,
                remark);
        //成功レスポンスを返す
        return ApiResponse.success("更新に成功しました");
    }
}