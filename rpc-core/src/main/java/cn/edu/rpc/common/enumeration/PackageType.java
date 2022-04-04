package cn.edu.rpc.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标识是请求包还是返回包
 */
@AllArgsConstructor
@Getter
public enum PackageType {

    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;

}
