package cn.edu.test;


import cn.edu.api.HelloObject;
import cn.edu.api.HelloService;
import cn.edu.rpc.serializer.CommonSerializer;
import cn.edu.rpc.transport.RpcClient;
import cn.edu.rpc.transport.RpcClientProxy;
import cn.edu.rpc.transport.netty.client.NettyClient;

/**
 * 测试用Netty消费者
 *
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.DEFAULT_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }

}
