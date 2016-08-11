package com.ifenwang.sco.common.storage.mapper;

import com.ifenwang.sco.common.storage.entity.Credential;

/**
 * 用户凭证接口
 */
public interface CredentialMapper
{
    /**
     * 保存一条凭证
     * @param credential
     * @return
     */
    int save(Credential credential);

    /**
     * 修改一条凭证
     * @param credential
     * @return
     */
    int update(Credential credential);

    /**
     * 根据凭证ID查询信息
     * @param credential
     * @return
     */
    Credential queryByPK(String credential);

}