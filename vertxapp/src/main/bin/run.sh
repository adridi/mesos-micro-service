#!/bin/sh

# Application name
APPLICATION_NAME=vertxapp
# Application description
APPLICATION_DESC="vertx app rest"
# Application version
APPLICATION_VERSION="1.0"
# The directory in which your application is installed
APPLICATION_DIR=$APPLICATION_NAME
# The directory where all config files are located
APPLICATION_CONF=$APPLICATION_DIR/conf
# The directory where all jar files are located
APPLICATION_LIB=$APPLICATION_DIR/lib
# The fat jar containing your application
APPLICATION_JAR=$APPLICATION_DIR/lib/$APPLICATION_NAME-$APPLICATION_VERSION-fat.jar
# full Main class name
APPLICATION_MAIN_CLASS=com.foresee.vertx.AppMain
# The application argument such as -cluster -cluster-host ...
APPLICATION_ARGS="-conf $APPLICATION_CONF/conf.json"
# Java options and system properties.
JAVA_OPTS=""
# The Java command to use to launch the application (must be java 8+)
JAVA=/opt/mesosphere/bin/java

NEW_PORT=$1
sed -i -e "s/8087/$NEW_PORT/g" $APPLICATION_CONF/conf.json

$JAVA $JAVA_OPTS -jar $APPLICATION_JAR $APPLICATION_ARGS

