RED='\033[0;31m'
NC='\033[0m'
CONFIG_FILE='./config.txt'
printf "Let's set up your ${RED}Arena Realm${NC} environment.\n"
read -rp "First, copy the path to your key file (.pem) including the key file in the path: " KEYFILE
read -rp "Next, copy the path to your client minecraft's mods directory: " CLIENT_MOD_DIR
read -rp "Enter the public DNS of the EC2 instance hosting the dev server: " AWS_DNS
read -rp "Finally, Enter the full path of the server minecraft's mod directory: " SERVER_MOD_DIR
KEYFILE="$(echo $KEYFILE | sed 's/\\/\//g')"
CLIENT_MOD_DIR="$(echo $CLIENT_MOD_DIR | sed 's/\\/\//g')"
SERVER_MOD_DIR="$(echo $SERVER_MOD_DIR | sed 's/\\/\//g')"
printf "KEYFILE=%s\nCLIENT_MOD_DIR=%s\nAWS_DNS=%s\nSERVER_MOD_DIR=%s" $KEYFILE $CLIENT_MOD_DIR $AWS_DNS $SERVER_MOD_DIR > $CONFIG_FILE

./gradlew setupDecompWorkspace
./gradlew eclipse
