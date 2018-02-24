package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

/**
 * 主机模型
 * Created by Shibo on 17/1/5.
 */
public class HostDO extends BaseModel{

    private int id;
    private String hostName;
    private String version;
    private String hostAddress;
    private int port;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
