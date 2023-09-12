FROM amazoncorretto:8
COPY ./app/build/libs/vpn_keep_alive.jar .
CMD java -jar ./vpn_keep_alive.jar ${URL} ${INTERVAL}