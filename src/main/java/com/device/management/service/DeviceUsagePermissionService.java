package com.device.management.service;

import com.device.management.dto.DeviceUsagePermissionDTO;
import com.device.management.entity.DeviceUsagePermission;
import com.device.management.exception.ResourceNotFoundException;
import com.device.management.repository.DeviceUsagePermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class DeviceUsagePermissionService {

    @Autowired
    private DeviceUsagePermissionRepository permissionRepository;

    /**
     * IDに基づいて権限の詳細を照会する
     *  1. 権限IDをパラメータとして受け取る
     *  2. リポジトリ層を通じて対応する権限エンティティを検索する
     *  3. 検索できない場合はリソース未発見の例外をスローする
     *  4. エンティティオブジェクトをDTOオブジェクトに変換する
     *  5. DTOオブジェクトを呼び出し元に返す
     * @param permissionId 権限ID
     * @return 権限詳細情報DTOオブジェクト
     */
    @Transactional(readOnly = true)
    public DeviceUsagePermissionDTO findPermissionDetail(String permissionId) {
        log.info("権限の詳細を確認する，permissionId: {}", permissionId);

        //IDに基づいてデータベースから権限オブジェクトを取得する
        DeviceUsagePermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> {
                    log.error("権限情報が存在しません，permissionId: {}", permissionId);
                    //存在しなければ、例外を投げる
                    return new ResourceNotFoundException("権限情報が存在しません，permissionId: " + permissionId);
                });

        // エンティティオブジェクトをDTOオブジェクトに変換して返却する
        return convertToDTO(permission);
    }

    /** フィールドに基づいて権限情報を更新する */

    /**
     * フィールドに基づいて権限情報を更新する
     *  1. IDに基づいて既存の権限エンティティを検索する
     *  2. 各パラメータに値があるかを確認する（StringUtilsを使用して文字列を判定）
     *  3. 値があるパラメータを既存のエンティティに更新する
     *  4. 更新者を"JS111"に設定する（後で機能を追加し、ログインユーザーを直接取得する予定）
     *  5. 更新後のエンティティを保存する
     * @param permissionId      権限ID
     * @param smartitStatusId   SmartITステータスID
     * @param noSmartitReason   SmartITがない理由
     * @param usbStatusId       USBステータスID
     * @param usbReason         USBの理由
     * @param usbExpireDate     USB有効期限
     * @param antivirusStatusId ウイルス対策ソフトステータスID
     * @param noSymantecReason  ウイルス対策ソフトがない理由
     * @param domainStatusId    ドメインステータスID
     * @param domainGroup       ドメイングループ
     * @param noDomainReason    ドメインがない理由
     * @param remark            備考
     */
    @Transactional
    public void updatePermissionByFields(String permissionId,
                                         String smartitStatusId,
                                         String noSmartitReason,
                                         String usbStatusId,
                                         String usbReason,
                                         LocalDate usbExpireDate,
                                         String antivirusStatusId,
                                         String noSymantecReason,
                                         String domainStatusId,
                                         String domainGroup,
                                         String noDomainReason,
                                         String remark
                                         ) {

        log.info("フィールドに基づいて権限情報を更新する，ID: {}", permissionId);

        //既存の権限エンティティを照会する
        DeviceUsagePermission existing = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("権限情報が存在しません"));

        //パラメータを一つずつ確認して更新する
        if (StringUtils.hasText(noSmartitReason)) {
            existing.setNoSmartitReason(noSmartitReason);
        }
        if (StringUtils.hasText(usbReason)) {
            existing.setUsbReason(usbReason);
        }
        if (StringUtils.hasText(noSymantecReason)) {
            existing.setNoSymantecReason(noSymantecReason);
        }
        if (StringUtils.hasText(domainGroup)) {
            existing.setDomainGroup(domainGroup);
        }
        if (StringUtils.hasText(noDomainReason)) {
            existing.setNoDomainReason(noDomainReason);
        }
        if (StringUtils.hasText(remark)) {
            existing.setRemark(remark);
        }
        //更新者を設定する（現在のユーザーを取得）
        //        getIdFromToken("")
            existing.setUpdater("JS111");

        if (usbExpireDate != null) {
            existing.setUsbExpireDate(usbExpireDate);
        }

        //保存して更新
        permissionRepository.save(existing);
        log.info("権限情報の更新が完了しました，permissionId: {}", permissionId);
    }

    /**
     * DTOへの変換メソッド
     * エンティティオブジェクトをDTOオブジェクトに変換する方法
     * @param permission 権限エンティティオブジェクト
     * @return 変換後のDTOオブジェクト
     */
    private DeviceUsagePermissionDTO convertToDTO(DeviceUsagePermission permission) {
        DeviceUsagePermissionDTO dto = new DeviceUsagePermissionDTO();

        // 権限基本IDを設定
        dto.setPermissionId(permission.getPermissionId());

        // デバイス情報マッピング
        if (permission.getDevice() != null) {
            //デバイス情報
            dto.setDeviceId(permission.getDevice().getDeviceId());
            dto.setComputerName(permission.getDevice().getComputerName());
            dto.setDeviceModel(permission.getDevice().getDeviceModel());
            dto.setProject(permission.getDevice().getProject());
            dto.setDevRoom(permission.getDevice().getDevRoom());

            // ユーザー情報
            if (permission.getDevice().getUser() != null) {
                dto.setUserId(permission.getDevice().getUser().getUserId());
                dto.setUserName(permission.getDevice().getUser().getName());
                dto.setDeptId(permission.getDevice().getUser().getDeptId());
            }
        }

        // ドメインステータス情報
        if (permission.getDomainStatus() != null) {
            dto.setDomainStatus(permission.getDomainStatus().getDictItemName());
        }
        dto.setDomainGroup(permission.getDomainGroup());
        dto.setNoDomainReason(permission.getNoDomainReason());

        // SmartITステータス情報
        if (permission.getSmartitStatus() != null) {
            dto.setSmartitStatus(permission.getSmartitStatus().getDictItemName());
        }
        dto.setNoSmartitReason(permission.getNoSmartitReason());

        // USBステータス情報
        if (permission.getUsbStatus() != null) {
            dto.setUsbStatus(permission.getUsbStatus().getDictItemName());
        }
        dto.setUsbReason(permission.getUsbReason());
        dto.setUsbExpireDate(permission.getUsbExpireDate());

        // アンチウイルスソフトのステータス情報
        if (permission.getAntivirusStatus() != null) {
            dto.setAntivirusStatus(permission.getAntivirusStatus().getDictItemName());
        }
        dto.setNoSymantecReason(permission.getNoSymantecReason());

        //情報の作成と更新
        dto.setRemark(permission.getRemark());
        dto.setCreateTime(permission.getCreateTime());
        dto.setCreater(permission.getCreater());
        dto.setUpdateTime(permission.getUpdateTime());
        dto.setUpdater(permission.getUpdater());

        return dto;
    }
}