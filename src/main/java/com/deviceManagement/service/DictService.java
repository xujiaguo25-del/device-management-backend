package com.deviceManagement.service;

import com.deviceManagement.common.Result;
import com.deviceManagement.dto.DictResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： hexy
 * @Date： 2026/01/05 17:04
 * @Describe： 辞書管理サービスインターフェース
 */

public interface DictService {
    /** 辞書タイプのコードに基づいて辞書項目を検索する（sortで昇順に並べ替え）
    * @param dictTypeCode
    * @return 辞書項目リスト
    */
    Result<DictResponse> getDictItemsByTypeCode(String dictTypeCode);
}
