package com.urbanfit.apiserver.entity.bo;

import com.github.dockerjava.api.model.Info;
import com.urbanfit.apiserver.common.base.BaseModel;

/**
 * 主机
 * Created by Shibo on 17/1/5.
 */
public class HostBO extends BaseModel{

    /**
     * 正常
     */
    public static final String STATUS_NORMAL = "status_normal" ;

    /**
     * 连接失败
     */
    public static final String STATUS_CONNECTION_FAIL = "status_connection_fail" ;


    /** id */
    private int id;

    /**
     * 从docker获取的主机信息s
     */
    private Info dockerInfo;

    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info getDockerInfo() {
        return dockerInfo;
    }

    public void setDockerInfo(Info dockerInfo) {
        this.dockerInfo = dockerInfo;
    }

    public HostBO(int id, Info dockerInfo , String status) {
        this.id = id;
        this.dockerInfo = dockerInfo;
        this.status = status;
    }

    public HostBO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
