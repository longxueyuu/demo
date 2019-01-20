#!/bin/bash

# docker build . -t longxueyuu/gerrit
# docker run -p 9001:8081 -p 9000:81 -v /zqx/dockersharing/data/gerrit/git:/lxy/gerrit_site/git -v /zqx/dockersharing/data/gerrit/db:/lxy/gerrit_site/db --name gerrit longxueyuu/gerrit

set -ex
source /etc/profile

# echo `javac`

# echo `java`

# echo `java -version`

nginx

/usr/local/nexus/bin/nexus start

/lxy/gerrit_site/bin/gerrit.sh run
