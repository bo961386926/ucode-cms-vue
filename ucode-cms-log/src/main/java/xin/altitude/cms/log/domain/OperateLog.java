/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.cms.log.domain;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录表 oper_log
 *
 * @author ucode
 */
@Data
public class OperateLog {
    /**
     * 日志主键
     */
    private Long operId;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer businessType;

    /**
     * 业务类型数组
     */
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 请求url
     */
    private String operUrl;

    /**
     * 操作地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operTime;

    // public Long getOperId() {
    //     return operId;
    // }
    //
    // public void setOperId(Long operId) {
    //     this.operId = operId;
    // }
    //
    // public String getTitle() {
    //     return title;
    // }
    //
    // public void setTitle(String title) {
    //     this.title = title;
    // }
    //
    // public Integer getBusinessType() {
    //     return businessType;
    // }
    //
    // public void setBusinessType(Integer businessType) {
    //     this.businessType = businessType;
    // }
    //
    // public Integer[] getBusinessTypes() {
    //     return businessTypes;
    // }
    //
    // public void setBusinessTypes(Integer[] businessTypes) {
    //     this.businessTypes = businessTypes;
    // }
    //
    // public String getMethod() {
    //     return method;
    // }
    //
    // public void setMethod(String method) {
    //     this.method = method;
    // }
    //
    // public String getRequestMethod() {
    //     return requestMethod;
    // }
    //
    // public void setRequestMethod(String requestMethod) {
    //     this.requestMethod = requestMethod;
    // }
    //
    // public Integer getOperatorType() {
    //     return operatorType;
    // }
    //
    // public void setOperatorType(Integer operatorType) {
    //     this.operatorType = operatorType;
    // }
    //
    // public String getOperName() {
    //     return operName;
    // }
    //
    // public void setOperName(String operName) {
    //     this.operName = operName;
    // }
    //
    // public String getDeptName() {
    //     return deptName;
    // }
    //
    // public void setDeptName(String deptName) {
    //     this.deptName = deptName;
    // }
    //
    // public String getOperUrl() {
    //     return operUrl;
    // }
    //
    // public void setOperUrl(String operUrl) {
    //     this.operUrl = operUrl;
    // }
    //
    // public String getOperIp() {
    //     return operIp;
    // }
    //
    // public void setOperIp(String operIp) {
    //     this.operIp = operIp;
    // }
    //
    // public String getOperLocation() {
    //     return operLocation;
    // }
    //
    // public void setOperLocation(String operLocation) {
    //     this.operLocation = operLocation;
    // }
    //
    // public String getOperParam() {
    //     return operParam;
    // }
    //
    // public void setOperParam(String operParam) {
    //     this.operParam = operParam;
    // }
    //
    // public String getJsonResult() {
    //     return jsonResult;
    // }
    //
    // public void setJsonResult(String jsonResult) {
    //     this.jsonResult = jsonResult;
    // }
    //
    // public Integer getStatus() {
    //     return status;
    // }
    //
    // public void setStatus(Integer status) {
    //     this.status = status;
    // }
    //
    // public String getErrorMsg() {
    //     return errorMsg;
    // }
    //
    // public void setErrorMsg(String errorMsg) {
    //     this.errorMsg = errorMsg;
    // }
    //
    // public Date getOperTime() {
    //     return operTime;
    // }
    //
    // public void setOperTime(Date operTime) {
    //     this.operTime = operTime;
    // }
}
