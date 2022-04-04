package cn.edu.rpc.provider;

import cn.edu.rpc.common.enumeration.RpcError;
import cn.edu.rpc.common.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务注册表，保存服务端本地服务
 */
public class ServiceProviderImpl implements ServiceProvider{

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderImpl.class);

    private static final Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    @Override
    public <T> void addServiceProvider(T service, String serviceName) {
        if (serviceMap.containsKey(serviceName)) return;
        serviceMap.put(serviceName,service);
        logger.info("向接口: {} 注册服务: {}", service.getClass().getInterfaces(), serviceName);
    }

    @Override
    public Object getServiceProvider(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if (service == null){
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
