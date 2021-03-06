# starter-quartz

参考：
- [quartz-scheduler 官网](http://www.quartz-scheduler.org/)
- [任务 启动、暂停、恢复等](https://www.cnblogs.com/eelve/p/11333897.html)
- [任务 数据库集群](https://www.cnblogs.com/summerday152/p/14193968.html#%E8%87%AA%E5%8A%A8%E9%85%8D%E7%BD%AE%E8%BF%99%E9%87%8C%E6%BC%94%E7%A4%BAsimpleschedulebuilder)


此处列出了几个代表性的开源产品

| feature  | quartz                                                 | elastic-job-cloud                                            | xxl-job                                                      | antares                          | opencron                                                     |
| :------- | :----------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- | :------------------------------- | :----------------------------------------------------------- |
| 依赖     | mysql                                                  | jdk1.7+, zookeeper 3.4.6+ ,maven3.0.4+ ,mesos                | mysql ,jdk1.7+ , maven3.0+                                   | jdk 1.7+ , redis , zookeeper     | jdk1.7+ , Tomcat8.0+                                         |
| HA       | 多节点部署，通过竞争数据库锁来保证只有一个节点执行任务 | 通过zookeeper的注册与发现，可以动态的添加服务器。 支持水平扩容 | 集群部署                                                     | 集群部署                         | —                                                            |
| 任务分片 | —                                                      | 支持                                                         | 支持                                                         | 支持                             | —                                                            |
| 文档完善 | 完善                                                   | 完善                                                         | 完善                                                         | 文档略少                         | 文档略少                                                     |
| 管理界面 | 无                                                     | 支持                                                         | 支持                                                         | 支持                             | 支持                                                         |
| 难易程度 | 简单                                                   | 较复杂                                                       | 简单                                                         | 一般                             | 一般                                                         |
| 公司     | OpenSymphony                                           | 当当网                                                       | 个人                                                         | 个人                             | 个人                                                         |
| 高级功能 | —                                                      | 弹性扩容，多种作业模式，失效转移，运行状态收集，多线程处理数据，幂等性，容错处理，spring命名空间支持 | 弹性扩容，分片广播，故障转移，Rolling实时日志，GLUE（支持在线编辑代码，免发布）,任务进度监控，任务依赖，数据加密，邮件报警，运行报表，国际化 | 任务分片， 失效转移，弹性扩容 ， | 时间规则支持quartz和crontab ，kill任务， 现场执行，查询任务运行状态 |
| 缺点     | 没有管理界面，以及不支持任务分片等。不适用于分布式场景 | 需要引入zookeeper , mesos, 增加系统复杂度, 学习成本较高      | 调度中心通过获取 DB锁来保证集群中执行任务的唯一性， 如果短任务很多，随着调度中心集群数量增加，那么数据库的锁竞争会比较厉害，性能不好。 | 不支持动态添加任务               | 不适用于分布式场景                                           |
| 使用企业 | 大众化产品，对分布式调度要求不高的公司大面积使用       | 36氪，当当网，国美，金柚网，联想，唯品会，亚信，平安，猪八戒 | 大众点评，运满满，优信二手车，拍拍贷                         | —                                | —                                                            |
