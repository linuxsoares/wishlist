FROM openjdk:8-jre-alpine

COPY target/wishlist.jar wishlist.jar

CMD ["/usr/bin/java", "-jar", "/wishlist.jar"]
