package redis.manager.config;

public class RedisConfig {

    private String ip;
    private int port;
    private String password;
    private int db;
    //The maximum number of milliseconds that the client needs to wait when no connection is available. , default = -1, wait forever
    private long maxWaitMillis;
    // maximum number of connection supported by pool
    private int maxTotal;
    //maximum number of idle connection in the pool
    private int maxIdle;
    // minimum number of idle connection in the pool
    private int minIdle;
     // Specifies whether the client must wait when the resource pool is exhausted, set to true -> maxWaitMillis will be used
    // set to false -> block util pool have idle connection . should use default ~ true
    private boolean blockWhenExhausted;

    // Specifies whether connections will be validatsing the PING command before they are borrowed from the pool. Invalid connections will be removed from the pool.
    // should set FALSE when workload is heavy. reduce consumption cased by ping test.
    private boolean testOnBorrow;

    // Specifies whether connections will be validated by using the PING command before they are returned to the pool. Invalid connections will be removed from the pool.
    // should set FALSE when workload is heavy. reduce consumption cased by ping test.
    private boolean testOnReturn;

    // Specifies whether to enable the idle resource detection, default = false, should = true
    private boolean testWhileIdle;

    // The number of resources to be detected at each idle resource detection. default: 3, -1: is all connection
    private int numberTestsPerEvictionRun;

    // Specifies the cycle of idle resources detection. Unit: milliseconds., default = -1 ~ no idle resource be detected
    private int timeBetweenEvictionRunsMillis;

    //The minimum idle time in milliseconds of a resource in the resource pool. When the upper limit is reached, the idle resource will be evicted.
    // thời gian be nhat 1 idle duoc ton tai trong pool. default = 180000 ~ 30 m
    private int minEvictableIdleTimeMillis;
    private boolean sentinel = false;
    private String redisMaster = "redismaster";
    private String name;

}

