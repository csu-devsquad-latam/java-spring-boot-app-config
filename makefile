build:
	clear
	echo "Building app without test"
	mvn clean package -Dmaven.test.skip

build-test:
	clear
	echo "Build and test"
	mvn clean package
