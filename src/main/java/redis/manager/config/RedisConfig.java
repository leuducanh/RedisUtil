package redis.manager.config;

public class RedisConfig {

//    public static final class Name {
//        public static final String IP = "ip";
//        public static final String PORT = "port";
//        public static final String PASSWORD = "password";
//        public static final String DB = "db";
//        public static final String MAX_WAIT_MILLIS = "maxWaitMillis";
//        public static final String MAX_TOTAL = "maxtotal";
//        public static final String MAX_IDLE = "maxIdle";
//        public static final String MIN_IDLE = "minIdle";
//        public static final String BLOCK_WHEN_EXHAUSTED = "blockWhenExhausted";
//        public static final String TEST_ON_BORROW = "testOnBorrow";
//        public static final String TEST_ON_RETURN = "testOnReturn";
//        public static final String TEST_WHILE_IDLE = "testWhileIdle";
//        public static final String NUMBER_TESTS_PER_EVICTION_RUN = "numberTestsPerEvictionRun";
//        public static final String TIME_BETWEEN_EVICTION_RUN_MILLIS = "timeBetweenEvictionRunsMillis";
//        public static final String MIN_EVICTABLE_IDLE_TIME_MILLIS = "minEvictableIdleTimeMillis";
//        public static final String SENTINEL_ENABLE = "sentinelEnable";
//        public static final String REDIS_MASTER = "redisMaster";
//        public static final String THIS_REDIS_NAME = "thisRedisName";
//    }

    private String ip;
    private Integer port;
    private String password;
    private Integer db;
    //The maximum number of milliseconds that the client needs to wait when no connection is available. , default = -1, wait forever
    private Long maxWaitMillis;
    // maximum number of connection supported by pool
    private Integer maxTotal;
    //maximum number of idle connection in the pool
    private Integer maxIdle;
    // minimum number of idle connection in the pool
    private Integer minIdle;
     // Specifies whether the client must wait when the resource pool is exhausted, set to true -> maxWaitMillis will be used
    // set to false -> block util pool have idle connection . should use default ~ true
    private Boolean blockWhenExhausted;

    // Specifies whether connections will be validating the PING command before they are borrowed from the pool. Invalid connections will be removed from the pool.
    // should set FALSE when workload is heavy. reduce consumption cased by ping test.
    private Boolean testOnBorrow;

    // Specifies whether connections will be validated by using the PING command before they are returned to the pool. Invalid connections will be removed from the pool.
    // should set FALSE when workload is heavy. reduce consumption cased by ping test.
    private Boolean testOnReturn;

    // Specifies whether to enable the idle resource detection, default = false, should = true
    private Boolean testWhileIdle;

    // The number of resources to be detected at each idle resource detection. default: 3, -1: is all connection
    private Integer numberTestsPerEvictionRun;

    // Specifies the cycle of idle resources detection. Unit: milliseconds., default = -1 ~ no idle resource be detected
    private Integer timeBetweenEvictionRunsMillis;

    //The minimum idle time in milliseconds of a resource in the resource pool. When the upper limit is reached, the idle resource will be evicted.
    // thời gian be nhat 1 idle duoc ton tai trong pool. default = 180000 ~ 30 m
    private Integer minEvictableIdleTimeMillis;

    // Returns whether or not the pool serves threads waiting to borrow objects fairly.
    // True means that waiting threads are served as if waiting in a FIFO queue.
    // true các thread đang waiting wait theo thứ tự fifo, còn false thì không nhường nhau thằng nhanh thằng chờ lâu.
    // fair =true thì dễ đoán được thời gian bỏ ra , tất cả cùng nhanh hoặc cùng chậm
    //https://polycrystal.org/posts/2012-05-25-active-record-connection-pool-fairness.html#:~:text=A%20connection%20pool%20is%20fair,connection%2C%20jumping%20ahead%20in%20line.
    private Boolean fairness;

    // cách pool chọn idle connection để trare về, nếu là lifo = true thì sẽ là càng mới sẽ được trả về càng sớm
    // còn nếu  = false thì sẽ là fifo thì trả về thằng cũ nhất.
    // https://stackoverflow.com/questions/14937939/apache-commons-pool-lifo-vs-fifo
    private Boolean lifo;

    private Boolean sentinel = false;
    private String redisMaster = "redismaster";
    private String name;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Boolean getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Integer getNumberTestsPerEvictionRun() {
        return numberTestsPerEvictionRun;
    }

    public void setNumberTestsPerEvictionRun(Integer numberTestsPerEvictionRun) {
        this.numberTestsPerEvictionRun = numberTestsPerEvictionRun;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Boolean getSentinel() {
        return sentinel;
    }

    public void setSentinel(Boolean sentinel) {
        this.sentinel = sentinel;
    }

    public String getRedisMaster() {
        return redisMaster;
    }

    public void setRedisMaster(String redisMaster) {
        this.redisMaster = redisMaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getFairness() {
        return fairness;
    }

    public void setFairness(Boolean fairness) {
        this.fairness = fairness;
    }

    public Boolean getLifo() {
        return lifo;
    }

    public void setLifo(Boolean lifo) {
        this.lifo = lifo;
    }
}

