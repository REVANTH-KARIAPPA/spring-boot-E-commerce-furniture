FROM openjdk:11
EXPOSE 8090
ADD target/spring-boot-e-commerce-furniture.jar furniture.jar
ENTRYPOINT ["java","-jar","furniture.jar"]