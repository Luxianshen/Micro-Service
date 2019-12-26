#!/bin/sh

echo *******************************启动token *****************************
(nohup java -jar token-service.jar --name="tokenService" >/dev/null &);
echo *******************************启动gateway *****************************
(nohup java -jar gateway-service.jar --name="gatewayService"  >/dev/null &);
echo *******************************启动auth *****************************
(nohup java -jar auth-service.jar --name="authService" >/dev/null &);
echo *******************************启动user *****************************
(nohup java -jar user-service.jar --name="userService" >/dev/null &);
echo *******************************启动transmit *****************************
(nohup java -jar transmit-service.jar --name="transmitService" >/dev/null & );
echo *******************************启动request *****************************
(nohup java -jar transmit-request.jar --name="requestService" >/dev/null & );
echo ***************************启动 End*****************************