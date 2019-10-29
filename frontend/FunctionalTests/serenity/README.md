# Serenity Cucumber tests for BDSODataPage

- This project runs the functional tests in a docker container. This Dockerfile uses salientcrgt2/ft_base_chrome:latest for running the tests. Use the following steps to run the tests and gather the reports:

1. Run the tests
docker build -t <image_tag> .

2. Create a dummy container and attach a tag
docker create --name <container_tag> <image_tag>

3. Now copy the serenity report to host
docker cp <container_tag>:/usr/app/target/site/serenity ./results
