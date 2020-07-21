/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: AsyncConfiguration
 * Author:   冷酷的苹果
 * Date:     2020/7/21 8:56
 * Description: 创建线程池
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈创建线程池〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/21
 * @since 1.0.0
 */
@Configuration
@EnableAsync    //启动异步服务
public class AsyncConfiguration {

    //声明一个线程池（并指定线程池的名字）
    @Bean("area")
    public Executor asyncArea(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数5：线程池创建时候初始化的线程数
        executor.setCorePoolSize(10);
        //最大线程数5：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(10);
        //缓冲队列500：用来缓冲执行任务的队列
        executor.setQueueCapacity(500);
        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("DailyAsync-");
        executor.initialize();
        return executor;
    }
}
