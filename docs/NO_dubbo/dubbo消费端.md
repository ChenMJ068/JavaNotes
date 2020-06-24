#dubbo消费端
目录：
<!-- TOC -->
- [dubbo消费端](#dubbo消费端)
    - [1.创建代理类](#1.创建代理类)
        - [1.1配置初始化](#1.1配置初始化)
        - [1.2服务订阅](#1.2服务订阅)
        - [1.3返回默认的集群和容错Invoker](#1.3返回默认的集群和容错Invoker)
        - [1.4创建代理对象](#1.4创建代理对象)
    - [2.远程调用](#远程调用)
         - [2.1代理调用](#2.1代理调用)
         - [2.2容错负载](#2.2容错负载)
    - [dubbo extension机制](#dubbo extension机制)
<!-- /MarkdownTOC -->

## 1.创建代理类
消费端的核心类自然是ReferenceBean，这个类是在Spring解析Dubbo的reference自定义标签时，在DubboNamespaceHandler类中进行加载
的。Spring配置文件示例如下：
```xml
<dubbo:reference interface="com.tradecenter.facade.PayTradeFacade" tiomeout="2000" />
```

ReferenceBean类的内容非常丰富，逻辑也较为复杂，但抽丝剥茧后，最主要的功能有三个，如图所示，分别是配置初始化、服务订阅和创建
代理对象。
![ReferenceBean类功能](../../../docs/img/dubbo/ReferenceBean类功能.png)

ReferenceBean的实现原理：
![ReferenceBean](../../../docs/img/dubbo/ReferenceBean.png)

从上图可以看到，ReferenceBean继承了ReferenceConfig类，实现了FactoryBean、InitializingBean、DisposableBean和
ApplicationContextAware接口。FactoryBean接口主要是通过getObject方法返回对远程服务调用的代理类实现的。InitializingBean
接口为Bean提供了初始化方式，包括afterPropertiesSet方法，在初始化Bean的时候都会执行。DisposableBean接口提供了destroy
方法，在Bean销毁的时候能够回调执行。而实现ApplicationContextAware接口就可以得到ApplicationContext中的所有Bean。

Dubbo核心类ReferenceConfig继承了AbstractReferenceConfig、AbstractInterfaceConfig、AbstractMethodConfig和
AbstractConfig类，各类的说明如下：
- [AbstractConfig](#AbstractConfig):配置解析的工具方法和公共方法；
- [AbstractMethodConfig](#AbstractMethodConfig):封装了配置文件标签中方法级别的相关属性
- [AbstractInterfaceConfig](#AbstractInterfaceConfig):封装了配置文件标签中接口级别的相关属性
- [AbstractReferenceConfig](#AbstractReferenceConfig):封装了引用实例的默认配置，比如检查服务实例是否存在，是否使用泛化
接口、版本号等。

### 1.1 配置初始化
从ReferenceConfig的afterPropertiesSet方法入手，在历史版本中该方法是这样实现的。源码如下：
```java
public void afterPropertiesSet() throws Exception {
    //如果consumer未注册，则执行下面的内容
    if(getConsumer() == null){
        //根据ConsumerConfig.class类型从applicationContext中获取实例
        Map<String,ConsumerConfig> consumerConfigMap = applicationContext == null?null:BeanFactoryUtils
            .beansOfTypeIncludingAncestors(applicationContext, ConsumerConfig.class,false,false);
        
        //如果spring的ioc容器中存在这样的ConsumerConfig
        if(consumerConfigMap != null && consumerConfigMap.size() > 0){
            ConsumerConfig consumerConfig = null;
            //遍历ConsumerConfig
            for(ConsumerConfig config:consumerConfigMap.values() > 0){
                
                if(config.isDefault() == null || config.isDefault().booleanValue()){
                    //如果存在两个默认的ConsumerConfig，则报错
                    if(consumerConfig != null){
                         throw new IllegalArgumentException("Duplicate consumer confids:" + consumerConfig
                         + "and"+config);
                    }
                    consumerConfig = config;
                }
            }
            if(consumerConfig != null){
                //设置默认的ConsumerConfig
                setConsumer(consumerConfig);
            }
        }
    }
    
    // 省略... ...
    
     Boolean shouldInit = isInit();
     if (shouldInit == null && getConsumer() != null) {
        shouldInit = getConsumer().isInit();
     }
     if(shouldInit != null && shouldInit.booleanValue()){
         getObject();
     }
}
      
```

从上面的源码可以看出，这一步整体来说就是设置默认的consumer，consumer是默认配置，其实就是配置文件中的<dubbo:consumer/>，
当reference某些属性没有配置的时候可以采用consumer的默认配置。在上面的源码省略部分依次设置了Application、Module、
Registries、Monitor等配置。这些均在Spring解析自定义标签加载到Spring容器中，将容器的实例取出来设置到ReferenceBean中成为
默认配置。

在方法的最后调用FactoryBean中getObject()方法吗，里面会继续调用ReferenceConfig的init方法进行数据组装，最终将数据组装到
一个Map对象中。为以后创建的Dubbo URL,以及向ZooKeeper注册中心注册服务提供重要的依据。

### 1.2 服务订阅
在看createProxy()方法源码前，先了解几个概念开开胃：
- [Invoker](#Invoker):代表一个可执行的对象，可以是本地执行类的Invoker，比如provider端的服务实现类，通过反射实现最终的
方法调用。也可以是一个远程通信执行类的Invoker，consumer端通过接口与provider端进行远程通信，provider端利用本地Invoker
执行相应的方法并返回结果。还可以是聚合Invoker，consumer调用端可以将多个Invoker聚合成一个Invoker执行操作。
- [Protocol](#Protocol):通信协议。默认的Protocol是DubboProtocol，通过Protocol创建Invoker对象，默认的也就是DubboInvoker。
- [ProxyFactory](#ProxyFactory):对于Consumer端来说是通过ProxyFactory创建调用接口的代理对象，对于Provider端来说主要是包
装本地执行的Invoker类。ProxyFactory接口实现类有JdkProxyFactory和JavassistProxyFactory，而默认是JavassistProxyFactory。
JdkProxyFactory是利用JDK自带的Proxy来动态代理目标对象的远程通信Invoker类。JavassistProxyFactory是利用Javassist字节码
技术来创建的远程通信Invoker类。

源码来源于2.7.8版本：
```java
public class ReferenceConfig<T> extends ReferenceConfigBase<T> {
    // ......
    
    private T createProxy(Map<String, String> map) {
       
        if (shouldJvmRefer(map)) {
            URL url = new URL(LOCAL_PROTOCOL, LOCALHOST_VALUE, 0, interfaceClass.getName()).addParameters(map);
            invoker = REF_PROTOCOL.refer(interfaceClass, url);
            if (logger.isInfoEnabled()) {
                logger.info("Using injvm service " + interfaceClass.getName());
            }
        } else {
            urls.clear();
            // 用户指定URL,指定的URL可能是点对点直连地址，也可能是注册中心URL
            if (url != null && url.length() > 0) { 
                String[] us = SEMICOLON_SPLIT_PATTERN.split(url);
                if (us != null && us.length > 0) {
                    for (String u : us) {
                        URL url = URL.valueOf(u);
                        if (StringUtils.isEmpty(url.getPath())) {
                            url = url.setPath(interfaceName);
                        }
                        if (UrlUtils.isRegistry(url)) {
                            urls.add(url.addParameterAndEncoded(REFER_KEY, StringUtils.toQueryString(map)));
                        } else {
                            urls.add(ClusterUtils.mergeUrl(url, map));
                        }
                    }
                }
            } else { 
                if (!LOCAL_PROTOCOL.equalsIgnoreCase(getProtocol())) {
                    //检查注册表配置是否存在
                    checkRegistry();
                    //通过注册中配置拼装URL
                    List<URL> us = ConfigValidationUtils.loadRegistries(this, false);
                    if (CollectionUtils.isNotEmpty(us)) {
                        for (URL u : us) {
                            URL monitorUrl = ConfigValidationUtils.loadMonitor(this, u);
                            if (monitorUrl != null) {
                                map.put(MONITOR_KEY, URL.encode(monitorUrl.toFullString()));
                            }
                            urls.add(u.addParameterAndEncoded(REFER_KEY, StringUtils.toQueryString(map)));
                        }
                    }
                    if (urls.isEmpty()) {
                        throw new IllegalStateException("No such any registry to reference " 
                        + interfaceName + " on the consumer " + NetUtils.getLocalHost() 
                        + " use dubbo version " + Version.getVersion() 
                        + ", please config <dubbo:registry address=\"...\" /> to your spring config.");
                    }
                }
            }

            if (urls.size() == 1) {
                invoker = REF_PROTOCOL.refer(interfaceClass, urls.get(0));
            } else {
                List<Invoker<?>> invokers = new ArrayList<Invoker<?>>();
                URL registryURL = null;
                //Invokers存放的是所有可用的服务调用者
                for (URL url : urls) {
                    invokers.add(REF_PROTOCOL.refer(interfaceClass, url));
                    if (UrlUtils.isRegistry(url)) {
                        registryURL = url; // use last registry url
                    }
                }
                // 有注册中心协议的url
                if (registryURL != null) { 
                    // 对有注册中心的Cluster，只用AvailableCluster
                    URL u = registryURL.addParameterIfAbsent(CLUSTER_KEY, ZoneAwareCluster.NAME);
                    // 加入集群，内部会做一些负载处理
                    invoker = CLUSTER.join(new StaticDirectory(u, invokers));
                } else { //不是注册中心的url
                    invoker = CLUSTER.join(new StaticDirectory(invokers));
                }
            }
        }

        if (shouldCheck() && !invoker.isAvailable()) {
            invoker.destroy();
            throw new IllegalStateException("Failed to check the status of the service "
                    + interfaceName
                    + ". No provider available for the service "
                    + (group == null ? "" : group + "/")
                    + interfaceName +
                    (version == null ? "" : ":" + version)
                    + " from the url "
                    + invoker.getUrl()
                    + " to the consumer "
                    + NetUtils.getLocalHost() + " use dubbo version " + Version.getVersion());
        }
        if (logger.isInfoEnabled()) {
            logger.info("Refer dubbo service " + interfaceClass.getName() + " from url " + invoker.getUrl());
        }
        /**
         * @since 2.7.0
         * ServiceData Store
         */
        String metadata = map.get(METADATA_KEY);
        WritableMetadataService metadataService = WritableMetadataService.
        getExtension(metadata == null ? DEFAULT_METADATA_STORAGE_TYPE : metadata);
        if (metadataService != null) {
            URL consumerURL = new URL(CONSUMER_PROTOCOL, map.remove(REGISTER_IP_KEY), 0, 
            map.get(INTERFACE_KEY), map);
            metadataService.publishServiceDefinition(consumerURL);
        }
        // 创建服务代理
        return (T) PROXY_FACTORY.getProxy(invoker, ProtocolUtils.isGeneric(generic));
    }
    
    // ......
}
```
这段代码主要表达了三个意思：
- 判断当前的服务是本地服务还是远程的；
- 根据SPI找到对应的Protocol类，生成对应的URL协议；
- 与注册中心进行交互，“watch”相应的节点。

逐次来分析一下这三个意思：

(1).判断当前的服务是本地服务还是远程的:

根据shouldJvmRefer方法中的isJVMRefer参数判断当前调用的是否是本地服务，本地服务可以理解为Provider端。
```java
protected boolean shouldJvmRefer(Map<String, String> map) {
        URL tmpUrl = new URL("temp", "localhost", 0, map);
        boolean isJvmRefer;
        if (isInjvm() == null) {
            // if a url is specified, don't do local reference
            if (url != null && url.length() > 0) {
                isJvmRefer = false;
            } else {
                // by default, reference local service if there is
                isJvmRefer = InjvmProtocol.getInjvmProtocol().isInjvmRefer(tmpUrl);
            }
        } else {
            isJvmRefer = isInjvm();
        }
        return isJvmRefer;
    }
```

(2).根据SPI找到对应的Protocol类，生成对应的URL协议:

在createProxy()方法源码中有调用ConfigValidationUtils.loadRegistries(this, false)方法。该方法的作用是装入Registry URL协
议，其本质就是将ZooKeeper URL协议更换为Registry URL协议。方法的的核心代码如下：
```java
List<URL> urls = UrlUtils.parseURLs(address, map);
for (URL url : urls) {
    url = URLBuilder.from(url)
        .addParameter(REGISTRY_KEY, url.getProtocol())
        .setProtocol(extractRegistryType(url))
        .build();
    
    if ((provider && url.getParameter(REGISTER_KEY, true))
        || (!provider && url.getParameter(SUBSCRIBE_KEY, true))) {
        registryList.add(url);
    }
}
```

在createProxy()方法源码中有如下代码，载入相关的Protocol协议类：
```java
if (urls.size() == 1) {
    invoker = REF_PROTOCOL.refer(interfaceClass, urls.get(0));
} 
```
代码在执行REF_PROTOCOL.refer()方法时，会调用到ProtocolFilterWrapper类中，在ProtocolFilterWrapper类中的实现是：
```java
 public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        if (UrlUtils.isRegistry(url)) {
            return protocol.refer(type, url);
        }
        return buildInvokerChain(protocol.refer(type, url), REFERENCE_FILTER_KEY, CommonConstants.CONSUMER);
 }
```
```java
//贴出UrlUtils.isRegistry(url)
public static boolean isRegistry(URL url) {
    return REGISTRY_PROTOCOL.equals(url.getProtocol()) || 
    SERVICE_REGISTRY_PROTOCOL.equalsIgnoreCase(url.getProtocol());
}
```
在refer()中先判断当前是不是Registry URL协议，如果是，则直接调用RegistryProtocol执行；如果不是，则将Protocol对象加入调用链。

(3).与注册中心进行交互，“watch”相应的节点:

官网上的Dubbo与注册中心的结构图，
![dubbo architecture](../../../docs/img/dubbo/dubbo architecture.png)

从图中可以看出，服务提供者Provider向服务注册中心Registry注册服务，而消费者Consumer从服务注册中心订阅所需要的服务，但不是所有
服务。当有新的Provider出现，或者现有的Provider宕机时，注册中Registry都会尽早发现，并将新的provider列表推送给对应的Consumer。
有了这样的机制，Dubbo才能做到Failover，而Failover的时效性，由注册中Registry的实现决定。

Dubbo支持多种注册中心，最常用的ZooKeeper。因为太多分布式的中间件需要依赖ZooKeeper作为协作者。那么Dubbo是怎么知道我用了哪种
实现作为注册中心呢？我们只需要在Dubbo的XML配置文件中配置dubbo:registry即可
```xml
<dubbo:registry id="registry" protocol="zookeeper" address="127.0.0.1"/>
```

在第二步中有提过RegistryProtocol类，通过这个类进行服务订阅相关工作。和上面一样在分析源码前先了解几个关键类：
- [ZooKeeperRegistry](#ZooKeeperRegistry):负责与ZooKeeper进行交互；
- [RegistryProtocol](#RegistryProtocol):从注册中心获取可用服务，或者将服务注册到ZooKeeper，然后提供服务或者调用代理；
- [RegistryDirectory](#RegistryDirectory):维护所有可用的远程Invoker或者本地的Invoker，这个类实现了NotifyListener；
- [NotifyListener](#NotifyListener):负责RegistryDirectory与ZooKeeperRegistry的通信；
- [FailbackRegistry](#FailbackRegistry):继承自Registry，实现了失败重试机制。

![类的继承关系](../../../docs/img/dubbo/类的继承关系.png)

在RegistryProtocol类的refer方法中主要通过getRegistry方法获取ZooKeeperRegistry实例，并将ZooKeeperRegistry实例以参数的方式
传入doRefer方法。代码如下：

```java
public class RegistryProtocol implements Protocol {
    //......
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        url = getRegistryUrl(url);
        //获取ZooKeeperRegistry实例
        Registry registry = registryFactory.getRegistry(url);
        if (RegistryService.class.equals(type)) {
            return proxyFactory.getInvoker((T) registry, type, url);
        }

        // group="a,b" or group="*"
        Map<String, String> qs = StringUtils.parseQueryString(url.getParameterAndDecoded(REFER_KEY));
        String group = qs.get(GROUP_KEY);
        if (group != null && group.length() > 0) {
            if ((COMMA_SPLIT_PATTERN.split(group)).length > 1 || "*".equals(group)) {
                return doRefer(getMergeableCluster(), registry, type, url);
            }
        }
        return doRefer(cluster, registry, type, url);
    }    

    //......

    private <T> Invoker<T> doRefer(Cluster cluster, Registry registry, Class<T> type, URL url) {
        //创建RegistryDirectory实例
        RegistryDirectory<T> directory = new RegistryDirectory<T>(type, url);
        //将ZooKeeperRegistry实例注入RegistryDirectory，形成组合关系
        directory.setRegistry(registry);
        //将RegistryProtocol实例注入RegistryDirectory，形成组合关系
        directory.setProtocol(protocol);
        //REFER_KEY的所有属性
        Map<String, String> parameters = new HashMap<String, String>(directory.getConsumerUrl().getParameters());
        //生成consumer端URL协议
        URL subscribeUrl = new URL(CONSUMER_PROTOCOL, parameters.remove(REGISTER_IP_KEY),
            0, type.getName(), parameters);
        if (directory.isShouldRegister()) {
            //调用registry实例进行消费者地址注册
            directory.setRegisteredConsumerUrl(subscribeUrl);
            registry.register(directory.getRegisteredConsumerUrl());
        }
        directory.buildRouterChain(subscribeUrl);
        //服务订阅
        directory.subscribe(toSubscribeUrl(subscribeUrl));

        //返回集群和容错
        Invoker<T> invoker = cluster.join(directory);
        List<RegistryProtocolListener> listeners = findRegistryProtocolListeners(url);
        if (CollectionUtils.isEmpty(listeners)) {
            return invoker;
        }

        RegistryInvokerWrapper<T> registryInvokerWrapper 
        = new RegistryInvokerWrapper<>(directory, cluster, invoker, subscribeUrl);
        for (RegistryProtocolListener listener : listeners) {
            listener.onRefer(this, registryInvokerWrapper);
        }
        return registryInvokerWrapper;
    }
}
```

- 消费者地址注册  
通过FailBackRegistry实例的registry方法调用ZooKeeperRegistry实例的doRegister方法实现消费者的注册地址注册

- 服务订阅  
通过FailBackRegistry实例的subscribe方法调用ZooKeeperRegistry实例的doSubscribe方法实现消费者的地址注册。


### 1.3返回默认的集群和容错Invoker

在上面的源码中有代码行
```java
Invoker<T> invoker = cluster.join(directory);
```
执行 cluster.join(directory)时会先进入MockClusterWrapper类中，代码如下：
```java
public class MockClusterWrapper implements Cluster {

    private Cluster cluster;

    public MockClusterWrapper(Cluster cluster) {
        this.cluster = cluster;
    }

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        return new MockClusterInvoker<T>(directory,
                //默认返回FailoverCluster实例
                this.cluster.join(directory));
    }
}
```
在jion方法中新生成的MockClusterInvoker实例，并将FailoverCluster实例的join方法返回的Invoker对象作为构造参数传递给
MockClusterInvoker对象。至于MockClusterWrapper实例为什么会在默认的FailoverCluster之前，请参考Dubbo SPI机制的内容。在
FailoverCluster实例中返回的是FailoverClusterInvoker对象，这是Dubbo默认的集群容错策略，当服务出现失败时，重试其他的服务器，
但是重试会带来较长的延长时间。最终MockClusterInvoker实例作为创建代理对象的方法参数传入。

### 1.4创建代理对象
在配置初始化和服务注册与订阅完成后，剩下的工作就是对服务接口类进行包装，产生代理对象并返回。

ReferenceConfig类的createProxy方法最后一行代码是：
```java
return (T) PROXY_FACTORY.getProxy(invoker, ProtocolUtils.isGeneric(generic));
```
Dubbo实现代理对象的方式有两种，一种是使用JDK动态代理，使用的是JDKProxyFactory；另一种是使用Javassist字节码来实现的，使用
JavassistProxyFactory来实现。Dubbo默认使用的是JavassistProxyFactory，代码如下：
```java
public class JavassistProxyFactory extends AbstractProxyFactory {

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        return (T) Proxy.getProxy(interfaces).newInstance(new InvokerInvocationHandler(invoker));
    }
    
    //......
}
```
上面代码中的Proxy类并不是JDK自带的生成代理对象的Proxy类，而是Dubbo自己实现的，类路径为：
org.apache.dubbo.common.bytecode.Proxy，利用Javassist字节码技术生成代理。源码如下：
```java
public abstract class Proxy {
    //......
    public static Proxy getProxy(ClassLoader cl, Class<?>... ics) {
        //第一段代码主要是将服务接口的全路径名以分号的方式连接起来，存放cache对象中以便下次使用。
        
        //MAX_PROXY_COUNT = 65535,服务类接口长度不能大于65535
        if (ics.length > MAX_PROXY_COUNT) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ics.length; i++) {
            String itf = ics[i].getName();
            //如果服务类不是接口则报错
            if (!ics[i].isInterface()) {
                throw new RuntimeException(itf + " is not a interface.");
            }

            Class<?> tmp = null;
            try {
                //根据服务类的全路径名返回服务接口的class
                tmp = Class.forName(itf, false, cl);
            } catch (ClassNotFoundException e) {
            }

            if (tmp != ics[i]) {
                throw new IllegalArgumentException(ics[i] + " is not visible from class loader");
            }

            sb.append(itf).append(';');
        }

        // 将接口全路径名以分号连接起来，拼成key字符串
        String key = sb.toString();

        // 定义缓存对象
        final Map<String, Object> cache;
        synchronized (PROXY_CACHE_MAP) {
            cache = PROXY_CACHE_MAP.computeIfAbsent(cl, k -> new HashMap<>());
        }

        Proxy proxy = null;
        synchronized (cache) {
            do {
                Object value = cache.get(key);
                //从缓存中取实例，如果是Reference类型的则直接返回代理
                if (value instanceof Reference<?>) {
                    proxy = (Proxy) ((Reference<?>) value).get();
                    if (proxy != null) {
                        return proxy;
                    }
                }

                //判断此时的value是正在创建中的对象，使用wait进行等待，直到创建完成
                if (value == PENDING_GENERATION_MARKER) {
                    try {
                        cache.wait();
                    } catch (InterruptedException e) {
                    }
                } else {
                    //将key和PENDING_GENERATION_MARKER缓存
                    cache.put(key, PENDING_GENERATION_MARKER);
                    break;
                }
            }
            while (true);
        }
        //第一段结束
        
        //下面的部分是Javassist的核心部分，主要的逻辑就是自己注入代码生成代理类，将InvokerInvocationHandler实例对象
        // 传入代理类，最终实现代理的功能。
        long id = PROXY_CLASS_COUNTER.getAndIncrement();
        String pkg = null;
        //利用字节码生成对象实例工具
        ClassGenerator ccp = null, ccm = null;
        try {
            ccp = ClassGenerator.newInstance(cl);

            Set<String> worked = new HashSet<>();
            List<Method> methods = new ArrayList<>();

            for (int i = 0; i < ics.length; i++) {
                if (!Modifier.isPublic(ics[i].getModifiers())) {
                    String npkg = ics[i].getPackage().getName();
                    if (pkg == null) {
                        pkg = npkg;
                    } else {
                        if (!pkg.equals(npkg)) {
                            throw new IllegalArgumentException("non-public interfaces from different packages");
                        }
                    }
                }
                ccp.addInterface(ics[i]);

                for (Method method : ics[i].getMethods()) {
                    String desc = ReflectUtils.getDesc(method);
                    if (worked.contains(desc) || Modifier.isStatic(method.getModifiers())) {
                        continue;
                    }
                    if (ics[i].isInterface() && Modifier.isStatic(method.getModifiers())) {
                        continue;
                    }
                    worked.add(desc);

                    int ix = methods.size();
                    Class<?> rt = method.getReturnType();
                    Class<?>[] pts = method.getParameterTypes();
                    
                    //生成代理方法体
                    StringBuilder code = new StringBuilder("Object[] args = new Object[").append(pts.length).append("];");
                    for (int j = 0; j < pts.length; j++) {
                        code.append(" args[").append(j).append("] = ($w)$").append(j + 1).append(";");
                    }
                    code.append(" Object ret = handler.invoke(this, methods[").append(ix).append("], args);");
                    if (!Void.TYPE.equals(rt)) {
                        code.append(" return ").append(asArgument(rt, "ret")).append(";");
                    }

                    methods.add(method);
                    ccp.addMethod(method.getName(), method.getModifiers(), rt, pts, method.getExceptionTypes(), code.toString());
                }
            }

            if (pkg == null) {
                pkg = PACKAGE_NAME;
            }

            // 生成代理实例对象
            String pcn = pkg + ".proxy" + id;
            //设置代理实例对象的类名
            ccp.setClassName(pcn);
            //添加静态Method属性
            ccp.addField("public static java.lang.reflect.Method[] methods;");
            //添加InvokerInvocationHandler属性
            ccp.addField("private " + InvocationHandler.class.getName() + " handler;");
            //添加构造方法，参数是InvokerInvocationHandler对象
            ccp.addConstructor(Modifier.PUBLIC, new Class<?>[]{InvocationHandler.class}, new Class<?>[0], "handler=$1;");
            ccp.addDefaultConstructor();
            //生成代理类Class
            Class<?> clazz = ccp.toClass();
            clazz.getField("methods").set(null, methods.toArray(new Method[0]));

            // 创建代理类对象
            String fcn = Proxy.class.getName() + id;
            ccm = ClassGenerator.newInstance(cl);
            ccm.setClassName(fcn);
            //添加默认构造方法
            ccm.addDefaultConstructor();
            //设置父类是抽象类Proxy
            ccm.setSuperClass(Proxy.class);
            //生成新的方法，实例化代理实例对象并返回
            ccm.addMethod("public Object newInstance(" + InvocationHandler.class.getName() + " h){ return new " + pcn + "($1); }");
            Class<?> pc = ccm.toClass();
            //实例化代理类对象
            proxy = (Proxy) pc.newInstance();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            // release ClassGenerator
            if (ccp != null) {
                ccp.release();
            }
            if (ccm != null) {
                ccm.release();
            }
            synchronized (cache) {
                if (proxy == null) {
                    cache.remove(key);
                } else {
                    cache.put(key, new WeakReference<Proxy>(proxy));
                }
                cache.notifyAll();
            }
        }
        return proxy;
    }    
}
```

到这里ReferenceBean整个类的源码基本分析完了，最终会使用InvokerInvocationHandler将服务接口包装成一个代理类并返回。我们在调用
服务接口的时候就会触发代理类，通过代理类实现服务路由、服务选取，以及与服务提供者Provider端的远程通信，这些过程服务调用者是无法
感知的，就像在应用中调用本地方法一样简单。虽然使用简单，但在性能上和调用本地方法却有很大的差别，我们不仅要考虑服务提供者Provider
的性能，还要考虑网络环境的健康状况。服务调用方根据返回的不同状态信息使用不同的策略应对。而Dubbo已经为我们提供了多种策略，下面
看一下InvokerInvocationHandler代理类的实现过程。

## 2.远程调用

### 2.1代理调用

### 2.2容错负载





