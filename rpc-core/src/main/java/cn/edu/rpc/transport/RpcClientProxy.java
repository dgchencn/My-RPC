package cn.edu.rpc.transport;

import cn.edu.rpc.protocol.RpcRequest;
import cn.edu.rpc.protocol.RpcResponse;
import cn.edu.rpc.transport.netty.client.NettyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * RPC客户端动态代理
 */
public class RpcClientProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private final RpcClient client;

    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),// 目标类的类加载
                new Class[]{clazz},// 代理需要实现的接口，可指定多个
                this// 代理对象对应的自定义 InvocationHandler
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法: {}#{}", method.getDeclaringClass().getName(), method.getName());
        RpcRequest rpcRequest = new RpcRequest(
                UUID.randomUUID().toString(),
                method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes(),false
        );
        RpcResponse rpcResponse = null;
        if (client instanceof NettyClient) {
            try {
                CompletableFuture<RpcResponse> completableFuture = (CompletableFuture<RpcResponse>) client.sendRequest(rpcRequest);
                rpcResponse = completableFuture.get();
            } catch (Exception e){
                logger.error("远程方法调用请求发送失败",e);
            }
        }

        return rpcResponse.getData();
    }
}
