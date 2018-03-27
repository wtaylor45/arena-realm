CONFIG_FILE='./config.txt'
source $CONFIG_FILE
BUILD_DIR='./build/libs/'
MODID='arenarealm'
VERSION='0.0.1'
JAR_PATH="${BUILD_DIR}${MODID}-${VERSION}.jar"
SERVER_PATH="${AWS_DNS}:${SERVER_MOD_DIR}"
./gradlew build
scp -i ${KEYFILE} ${JAR_PATH} ec2-user@${SERVER_PATH}
cp ${JAR_PATH} ${CLIENT_MOD_DIR}
