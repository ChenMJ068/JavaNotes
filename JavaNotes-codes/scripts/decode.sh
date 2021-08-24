#!/bin/sh
###############
#对密码解密
###############

USERNAME=`sed -n 1p $HOME/.pass_file`
PASSWORD=`sed -n 2p $HOME/.pass_file`
DBSID   =`sed -n 3p $HOME/.pass_file`

function decstr(){
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

    usresult=`echo $user |openssl enc -aes-256-cfb -d -base64 -k $str_key`
    psresult=`echo $pass |openssl enc -aes-256-cfb -d -base64 -k $str_key`

    echo $usresult"/"$psresult
    return 0
}

result=`decstr $USERNAME $PASSWORD`
echo $result"@"$DBSID