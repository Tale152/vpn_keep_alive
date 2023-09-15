FROM gradle:jdk8
COPY . ./vpn_keep_alive
WORKDIR ./vpn_keep_alive
RUN chmod 777 ./gradlew
RUN ./gradlew clean build --stacktrace
WORKDIR ..
RUN mv ./vpn_keep_alive/app/build/libs/vpn_keep_alive.jar ./vpn_keep_alive.jar
RUN rm -R ./vpn_keep_alive
CMD java -jar ./vpn_keep_alive.jar ${URL} ${INTERVAL}