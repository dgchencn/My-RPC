package cn.edu.rpc.transport;

import cn.edu.rpc.protocol.RpcRequest;

/**
 * 客户端类通用接口
 */
public interface RpcClient {
    int DEFAULT_SERIALIZER = 0;
    Object sendRequest(RpcRequest rpcRequest);
}
