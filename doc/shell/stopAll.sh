gatewayService=`ps -auxww|grep gatewayService|grep -v grep|awk '{print $2}'`;
tokenService=`ps -auxww|grep tokenService|grep -v grep|awk '{print $2}'`;
authService=`ps -auxww|grep authService|grep -v grep|awk '{print $2}'`;
userService=`ps -auxww|grep userService|grep -v grep|awk '{print $2}'`;
transmitService=`ps -auxww|grep transmitService|grep -v grep|awk '{print $2}'`;
requestService=`ps -auxww|grep requestService|grep -v grep|awk '{print $2}'`;

if [ -n "$gatewayService" ]

then

echo "======to kill the gatewayService $gatewayService========"

kill $gatewayService

sleep 1

fi
if [ -n "$tokenService" ]

then

echo "======to kill the tokenService $tokenService========"

kill $tokenService

sleep 1

fi
if [ -n "$authService" ]

then

echo "======to kill the authService $authService========"

kill $authService

sleep 1

fi
if [ -n "$userService" ]

then

echo "======to kill the userService $userService========"

kill $userService

sleep 1

fi

if [ -n "$transmitService" ]

then

echo "======to kill the transmitService $transmitService========"

kill $transmitService

sleep 1

fi
if [ -n "$requestService" ]

then

echo "======to kill the requestService $requestService========"

kill $requestService

sleep 1

fi