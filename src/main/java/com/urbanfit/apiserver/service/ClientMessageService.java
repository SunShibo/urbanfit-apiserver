package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.ClientMessageDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/3/28.
 */
@Service("clientMessageService")
@Transactional
public class ClientMessageService {
    @Resource
    private ClientMessageDao clientMessageDao;

    
}
