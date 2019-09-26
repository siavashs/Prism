FROM circleci/openjdk:8-jdk as build
WORKDIR /home/circleci
# Install atlassian-plugin-sdk
RUN wget -nv https://packages.atlassian.com/api/gpg/key/public \
    && sudo apt-key add public \
    && sudo sh -c 'echo "deb https://packages.atlassian.com/atlassian-sdk-deb stable contrib" >> /etc/apt/sources.list' \
    && sudo apt-get -q -y install apt-transport-https \
    && sudo apt-get -q update \
    && sudo apt-get -q -y install atlassian-plugin-sdk
COPY --chown=circleci pom.xml .
# Get dependencies
RUN atlas-mvn --batch-mode dependency:go-offline
COPY --chown=circleci src src
# Build packages
RUN atlas-package --batch-mode \
    && mkdir artifacts/ \
    && mv target/prism-* artifacts/
# Save artifacts in small alpine image
FROM alpine
COPY --from=build --chown=root /home/circleci/artifacts /artifacts
