cd ../
#spawn mvn clean package
#interact
spawn scp -r xiaokai-main/target/xiaokai-0.0.1-SNAPSHOT.jar root@188.131.243.110:/root/project
expect "password:"
send "*\r"
interact
spawn ssh root@188.131.243.110
expect "password:"
send "*\r"
expect "*]#"
send "cd project\r"
send "docker restart xiaokai\r"
send "logout\r"
interact


