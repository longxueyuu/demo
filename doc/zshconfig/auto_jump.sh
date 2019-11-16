#!/usr/bin/expect

set INIT [lindex $argv 0]
set SERVER_ID [lindex $argv 1]
set GREP_WORD [lindex $argv 2]
set timeout 5

set GREP " | grep -E \"$GREP_WORD\" "
if {$argc <= 1} {
  set SERVER_ID "1"
}

if {$argc != 3} {
  set GREP " "
}

spawn ssh jp

expect {
    "yes/no"        {send "yes\r";exp_continue;}
    "Opt or ID>:"   {send "$SERVER_ID \r";}
}

expect {
  "ID>:"          {send "1\r";exp_continue;}
  -ex ":~$"        {send "$INIT $GREP\r";}
}

interact
