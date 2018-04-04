CONFIG_FILE='./config.txt'
source $CONFIG_FILE
BUILD_DIR='./build/libs/'
MODID='gladius'
VERSION='0.0.1'
JAR_PATH="${BUILD_DIR}${MODID}-${VERSION}.jar"
SERVER_PATH="${AWS_DNS}:${SERVER_MOD_DIR}"
./gradlew build
echo "Starting scp.."
cp -i "${JAR_PATH}" "${SERVER_MOD_DIR}"
echo "Starting cp..."
cp "${JAR_PATH}" "${CLIENT_MOD_DIR}"
