#!/bin/sh
###############
#对密码加密
###############

read -p "Enter your DB SID:" DBSID
read -p "Enter your USERANME:" USERANME
read -s -p "Enter your DB PASSWORD:" PASSWORD

FILE=$HOME/.pass_file

function encstr(){
    local user=$1
    local pass=$2
    local str_key=5
    local usresult=""
    local psresult=""

    if [ "X" = "X$user"];then
        return 1
    fi
    if [ "X" = "X$pass"];then
        return 2
    fi
    if [ "X" = "X$str_key"];then
        return 3
    fi

    usresult=`echo $user |openssl enc -aes-256-cfb -e -base64 -k $str_key -salt`
    psresult=`echo $pass |openssl enc -aes-256-cfb -e -base64 -k $str_key -salt`

    echo $usresult
    echo $psresult
    return 0
}

encstr $USERANME $PASSWORD >$HOME/.pass_file

echo $DBSID >> $HOME/.pass_file
exit;