package com.urbanfit.apiserver.entity.bo;

import com.github.dockerjava.api.DockerClient;
import com.urbanfit.apiserver.common.base.BaseModel;
import com.urbanfit.apiserver.entity.HostDO;

/**
 * dockerClient 的业务对象, 针对于dockerClient的装饰对象
 * Created by Shibo on 17/1/6.
 */
public class DockerClientBO extends BaseModel{

    /**
     * 正常
     */
    public static final String STATUS_NORMAL = "status_normal" ;

    /**
     * 连接失败
     */
    public static final String STATUS_CONNECTION_FAIL = "status_connection_fail" ;

    private HostDO hostInfo;
    private String status ;
    private DockerClient dockerClient;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DockerClient getDockerClient() {
        return dockerClient;
    }

    public void setDockerClient(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public HostDO getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(HostDO hostInfo) {
        this.hostInfo = hostInfo;
    }
}
