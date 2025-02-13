package team_json_delivery.sns_b.domain.feed.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurer {
    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        val processors = Runtime.getRuntime().availableProcessors()
        executor.corePoolSize = processors
        executor.maxPoolSize = processors * 2
        executor.queueCapacity = 50
        executor.keepAliveSeconds = 60
        executor.setThreadNamePrefix("AsyncExecutor-")
        executor.initialize()
        return executor
    }
}
