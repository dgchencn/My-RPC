# My-RPC 框架

------

My-RPC 是一个自学项目，是一款基于Netty的 RPC 框架，让分布式或者微服务系统中服务之间的调用像本地调用一样简单。技术栈：Netty+Jackson+Kyro+Nacos

## 项目模块

------

- **rpc-core** —— 框架核心实现
- **test-api** —— 通用服务接口
- **test-client** —— 测试消费侧
- **test-server** —— 测试提供侧

## 使用

------

在此之前请确保 Nacos 运行在本地 `8848` 端口。

首先启动服务提供者NettyTestServer，再启动消费者NettyTestClient，在消费侧收到服务侧返回的消息。

