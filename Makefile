.PHONY: build

build:
	./gradlew clean check

package:
	./gradlew clean bootJar -x test

clear:
	./gradlew clean
	docker compose -f docker/docker-compose.yml down -v

setup: build

test:
	./gradlew test

run-dev:
	./gradlew bootRun --args='--spring.profiles.active=dev'

run-dev-docker-db: docker-infra-start run-dev

start: run-dev-docker-db

docker-infra-start:
	npx kill-port 5432
	docker compose -f docker/docker-compose.yml up -d -V --remove-orphans

update-versions:
	./gradlew dependencyUpdates

#lint:
#	./gradlew checkstyleMain checkstyleTest