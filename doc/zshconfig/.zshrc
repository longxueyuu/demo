# 设置 JDK 8
export JAVA_8_HOME=`/usr/libexec/java_home -v 1.8`
# 设置 JDK 12
export JAVA_12_HOME=`/usr/libexec/java_home -v 12`

#默认JDK 8
export JAVA_HOME=$JAVA_8_HOME

#alias命令动态切换JDK版本
alias jdk8="export JAVA_HOME=$JAVA_8_HOME"
alias jdk12="export JAVA_HOME=$JAVA_12_HOME"


export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

# ssh auto
alias jump="${auto_jump_sh} '${init_command}'"


#maven
export MAVEN_HOME="/usr/local/Cellar/maven/${version}"

#mongodb
export MONGODB_HOME="/usr/local/Cellar/mongodb/${version}"

# go
export GOROOT="/usr/local/go"
export GOPATH="/Users/${user}/go"

export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$MONGODB_HOME/bin:$GOROOT/bin:$GOPATH/bin:$PATH
