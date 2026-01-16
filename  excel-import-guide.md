# 设备管理系统 Excel 导入模块使用说明

本说明面向后端/前端同事，介绍系统的Excel导入能力，包括：支持的文件格式与模板、使用方法、接口与参数、返回结果与异常处理、以及问题排查建议。

## 模块概述
- 支持 .xls（Excel 97-2003）与 .xlsx（Excel 2007+）两种格式的上传解析。
- 通过注解 ExcelColumn 实现 Excel 列到 DTO 字段的映射。
- 默认解析第一个 Sheet；可指定从某行开始读取以跳过表头区域。
- 自动跳过空行（整行没有有效数据时不入列表）。
- 根据目标字段类型进行自动类型转换（字符串、数值、布尔、日期时间等）。

核心位置：
- 工具类：[ExcelUtil.java](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/util/ExcelUtil.java)
- 映射注解：[ExcelColumn.java](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/annotation/ExcelColumn.java)
- 安全检查预览DTO：[SamplingCheckExcelDto.java](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/dto/SamplingCheckExcelDto.java)
- 设备导入DTO：[DeviceExcelDto.java](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/dto/DeviceExcelDto.java)

## 文件与模板约束
- 文件格式：后缀必须为 .xls 或 .xlsx，否则抛出“只支持 .xls 和 .xlsx 格式”的异常。
- 读取范围：仅处理第一个Sheet；通过 startRow 控制起始行（0表示第一行）。
- 表头行：如表头占多行，请将 startRow 设置为数据开始的行索引。
- 空行处理：整行无有效单元格值会被跳过。
- 单元格类型支持：
    - 字符串：trim后读取
    - 数值：普通数值按Double处理；日期单元格识别为Date
    - 公式：优先读取字符串，失败降级为数值
    - 布尔：true/false
    - 空白：视为“”
- 类型转换：
    - String：数值转字符串时去除“.0”，避免科学计数法（通过 BigDecimal）
    - 数值：Integer/Long/Double按需转换，非纯数字字符会被过滤
    - Boolean：支持 true/false、1/0
    - LocalDateTime：日期单元格转系统时区的LocalDateTime
    - 不支持的类型转换返回 null（并记录debug日志）

参考实现位置：
- 单元格取值与类型转换：[ExcelUtil.getCellValue/convertType](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/util/ExcelUtil.java#L183-L291)

## 接口说明

### 1. 安全检查导入预览
- 作用：将上传的安全检查Excel解析为列表供前端预览，不落库。
- 路由：
    - 推荐：POST /api/security-checks/import-preview
    - 兼容：POST /api/auth/security-checks/import-preview
- 控制器位置：[SamplingCheckController.importSamplingCheckPreview](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/controller/SamplingCheckController.java#L198-L208)
- 请求类型：multipart/form-data
- 请求参数：
    - file（必填）：Excel文件
    - startRow（选填，默认11）：起始行索引（0-based），用于跳过表头区域
- 返回：
    - ApiResponse<List<SamplingCheckExcelDto>>
    - code=200时，data为解析后的行列表；message含“解析成功”
- 字段映射（列索引基于模板）：
    - 0 编号 → rowNo
    - 1 工号 → userId
    - 2 姓名 → name
    - 3 设备编号 → deviceId
    - 4 开机认证 → bootAuthentication（字符串，常见值“○”或空）
    - 5 密码屏保 → screenSaverPwd
    - 6 安装软件 → installedSoftware
    - 7 安全补丁 → securityPatch
    - 8 病毒防护 → antivirusProtection
    - 9 USB接口 → usbInterface
    - 10 处置措施 → disposalMeasures

示例（Windows 内置 curl）：curl -X POST ^-H "Authorization: Bearer <token/>



### 2. 设备数据导入提交
- 作用：解析设备Excel并落库（DeviceInfo/DeviceIp等）。
- 路由：
    - 控制器声明为 @RequestMapping("/api/device") → POST /api/device/import
    - 若启用 server.servlet.context-path=/api，最终可能为 /api/api/device/import（注意重复“/api”）
    - 建议统一为类级不带“/api”，由 context-path 统一前缀；当前请按实际环境确认
- 控制器位置：[DeviceController.import](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/controller/DeviceController.java#L16-L19)
- 服务实现位置：[DeviceServiceImpl.importDeviceExcel](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/service/impl/DeviceServiceImpl.java#L31-L49)
- 请求类型：multipart/form-data
- 请求参数：
    - file（必填）：Excel文件
- 返回：
    - ApiResponse<String>，message内包含处理条数统计
- 主要字段映射与字典转换：
    - DeviceInfo：deviceId、deviceModel、computerName、loginUsername（优先Excel提供）、userId、project（暂映射department）、remark，以及 OS/内存/硬盘字典ID等
    - DeviceIp：支持多IP（按换行/逗号/分号分割）
    - 字典枚举映射：[DictEnum使用位置](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/service/impl/DeviceServiceImpl.java#L104-L133)

示例（Windows 内置 curl）：curl -X POST ^
-H "Authorization: Bearer <token/>
如发现 404，请尝试：http://localhost:8080/api/api/device/import
并确认当前 `server.servlet.context-path` 配置。

## 使用流程建议
- 安全检查：先调用“导入预览”获取解析结果 → 前端校验与确认 → 以业务接口（新增/更新）逐条提交或后续补充批量入库接口。
- 设备导入：直接调用“设备数据导入提交”，后端完成解析与入库；必要时先通过预览（待补充）进行数据清洗。
- DTO设计：为每种Excel模板定义对应的 DTO，并使用 @ExcelColumn 指定列索引与列名描述。

## 认证与安全
- 除登录/登出外，所有接口默认需要 JWT 鉴权。
- 在请求头添加：Authorization: Bearer <token>
- 相关配置：
    - 安全过滤链：[SecurityConfig.filterChain](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/config/SecurityConfig.java#L40-L59)
    - 允许匿名路由（示例）：JwtAuthenticationFilter.isPermittedUrl（含部分 /api/auth/**）
- 上传大小限制（可配置）：
    - spring.servlet.multipart.max-file-size=10MB
    - spring.servlet.multipart.max-request-size=10MB

## 返回与错误约定
- 标准返回结构：[ApiResponse](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/dto/ApiResponse.java)
    - 成功：code=200，message=成功/解析成功/导入成功
    - 失败：code根据场景可能为400/401/404/500等
- 常见错误：
    - 文件为空：返回 code=400（设备导入）或解析列表为空（预览）
    - 不支持的文件格式：抛异常“只支持 .xls 和 .xlsx 格式的Excel文件”
    - Excel解析失败：返回 code=500，message携带异常信息
    - 路径错误导致404：见下方“常见问题排查”
- 全局异常处理：
    - 统一封装为 ApiResponse，并按HTTP状态返回
    - 位置：[GlobalExceptionHandler](file:///c:/Users/caolh/Desktop/device-management-backend/src/main/java/com/device/management/exception/GlobalExceptionHandler.java)

## 常见问题排查
- 预览接口返回404（静态资源匹配）：
    - 通常是路由前缀不一致导致；当前已兼容 /api/security-checks/import-preview 与 /api/auth/security-checks/import-preview
- 设备导入接口路径疑似重复 /api/api：
    - 当控制器类级路径包含“/api”，且同时配置了 server.servlet.context-path=/api 时会出现；请按实际环境选择 /api/device/import 或 /api/api/device/import
- 解析结果为空：
    - 检查 startRow 是否正确；确认数据是否位于第一个Sheet；确认列索引与模板一致
- 类型转换失败：
    - Excel单元格内容与目标字段类型不匹配（例如将文字映射到数值字段）；可调整DTO字段为String或优化Excel内容

## 版本与依赖
- Apache POI：poi 5.2.3、poi-ooxml 5.2.3
- Spring Boot：3.2.0
- JDK：17

## 变更记录
- 2026-01-15：兼容新增路由 /api/auth/security-checks/import-preview（控制器类级映射支持双前缀）