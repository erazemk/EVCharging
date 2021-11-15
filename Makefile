ver =
name = EVCharging-$(shell git describe --tags --abbrev=0)-skupina00.zip

all: help

clean:
	mvn clean

help:
	@echo "Usage: make [option] (arguments)"
	@echo
	@echo "Options:"
	@echo "    clean               Remove project artifacts (~ mvn clean)"
	@echo "    help                Print this message"
	@echo "    package             Build and package project (~ mvn clean package)"
	@echo "    pull                Pull remote commits and tags (~ git pull)"
	@echo "    push                Push local commits and tags (~ git push)"
	@echo "    release             Update project version, create git tag and push it to remote (release a new version)"
	@echo "    tag (ver=X.Y.Z)     Update project version and create git tag (if without arguments print existing tags)"
	@echo "    zip (name=XYZ)      Create a .zip archive of the project (if without name use EVCharging-v<latest>-skupina00.zip"


package: clean
	mvn package

pull:
	git pull --prune --tags

push:
	git push --follow-tags

release: tag push

tag:
    ifndef ver
		git tag -l
    else
		sed -i '0,/<version>/{s/<version>.*<\/version>/<version>'$(ver)'<\/version>/}' pom.xml
		sed -i '0,/<version>/{s/<version>.*<\/version>/<version>'$(ver)'<\/version>/}' api/pom.xml
		sed -i '0,/<version>/{s/<version>.*<\/version>/<version>'$(ver)'<\/version>/}' services/pom.xml
		sed -i '0,/<version>/{s/<version>.*<\/version>/<version>'$(ver)'<\/version>/}' entities/pom.xml
		sed -i '0,/version/{s/version: .*/version: '$(ver)'/}' api/src/main/resources/config.yaml

		git add pom.xml api/pom.xml services/pom.xml entities/pom.xml api/src/main/resources/config.yaml
		git commit -qm "Bump project version number to $(ver)"
		git tag -asm "Release v$(ver)" v"$(ver)"
    endif

zip: clean
	zip -r -9 $(name) .
