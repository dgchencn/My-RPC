package cn.edu.test;

import cn.edu.rpc.annotation.ServiceScan;
import cn.edu.rpc.serializer.CommonSerializer;
import cn.edu.rpc.transport.RpcServer;
import cn.edu.rpc.transport.netty.server.NettyServer;

/**
 * 测试用Netty服务提供者（服务端）
 *
 */
@ServiceScan
public class NettyTestServer {

    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.DEFAULT_SERIALIZER);
        server.start();
    }

}
