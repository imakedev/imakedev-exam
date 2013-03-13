#!/bin/sh
#export JAVA_HOME=/usr/local/data/Java/jdk1.7.0_07
export JAVA_HOME=/usr/java/jdk/jdk1.7.0_11
cd /opt/agent/MC/
#0 0 * * *          -- midnight every day
#0 0 * * 1-5        -- midnight every weekday
#0 0 1,15 * *       -- midnight on 1st and 15th
#                      of month
#0 0 1 * 5          -- midnight on 1st of month
#                      and every Friday
#set fileformat=unix
#nohup sh /opt/agent/MC/missAgent.sh > /dev/null 2>&1 &
#-Xms128m -Xmx1024m -XX:MaxPermSize=512m
#${JAVA_HOME}/bin/java -Xms128m -Xmx1024m -XX:MaxPermSize=512m -cp .:lib/commons-codec-1.6.jar:lib/commons-logging-1.1.1.jar:lib/fluent-hc-4.2.3.jar:lib/httpclient-4.2.3.jar:lib/httpclient-cache-4.2.3.jar:lib/httpcore-4.2.2.jar:lib/httpmime-4.2.3.jar:lib/json-simple-1.1.1.jar:lib/log4j-1.2.17.jar:lib/mysql-connector-java-5.1.22-bin.jar:lib/thebluecodeAgent.jar th.co.aoe.imake.thebluecode.agent.NumberAgent
${JAVA_HOME}/bin/java -cp .:lib/commons-codec-1.6.jar:lib/commons-logging-1.1.1.jar:lib/log4j-1.2.17.jar:lib/mysql-connector-java-5.1.22-bin.jar:lib/MISSAgent.jar th.co.aoe.makedev.missconsult.agent.MissAgent
