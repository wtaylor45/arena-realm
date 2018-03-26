./gradlew build

scp -i minecraft-key.pem build/libs/arenarealm-0.0.1.jar ec2-user@ec2-34-197-181-44.compute-1.amazonaws.com:/home/ec2-user/minecraft/mods