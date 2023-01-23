## http://korea.gnu.org/manual/release/make/make-sjp/make-ko_toc.html
.PHONY : help clean test build run pull push stop-db start-db show-aws start-aws stop-aws start-es stop-es
.DEFAULT : xxx

PROFILE ?= local
GIT_CURRENT_BRANCH := $(shell git rev-parse --abbrev-ref HEAD)

LOCAL_DB_PORT ?= 27017
LOCAL_DB_NAME ?= sib-talk
LOCAL_DB_PASSWORD ?= sib-talk
LOCAL_DB_USER := $(LOCAL_DB_NAME)
LOCAL_DB_CONTAINER := local-$(LOCAL_DB_NAME)
RUNNING_DB_CONTAINER := $(shell docker ps -f name=$(LOCAL_DB_CONTAINER) --format "{{.Names}}")

LOCAL_REDIS_PORT ?= 6379
LOCAL_REDIS_CONTAINER := sib-talk-redis
RUNNING_REDIS_CONTAINER := $(shell docker ps -f name=$(LOCAL_REDIS_CONTAINER) --format "{{.Names}}")

GRADLE_TASKS = clean test build

## https://gist.github.com/prwhite/8168133#gistcomment-3785627
help: ## show help message
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[$$()% 0-9a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

${GRADLE_TASKS}:
ifdef MODULE
	./gradlew :$(MODULE):$@
else
	./gradlew $@
endif

clean: ## gradle clean

build: clean ## gradle build

stop-db: ## Stop DB Docker Container
ifeq ($(RUNNING_DB_CONTAINER),$(LOCAL_DB_CONTAINER))
	@docker stop $(LOCAL_DB_CONTAINER)
else
	@echo "DB($(LOCAL_DB_CONTAINER)) is not running"
endif

start-db: ## Start DB Docker Container
ifneq ($(RUNNING_DB_CONTAINER),$(LOCAL_DB_CONTAINER))
	@docker run --rm --name $(LOCAL_DB_CONTAINER) -d \
	-p $(LOCAL_DB_PORT):27017 \
	-e MONGO_INITDB_ROOT_PASSWORD="$(LOCAL_DB_PASSWORD)" \
	-e MONGO_INITDB_ROOT_USERNAME="$(LOCAL_DB_USER)" \
	-e MONGO_INITDB_ROOT_DATABASE="$(LOCAL_DB_NAME)"  \
	mongo:6.0
else
	@echo "DB($(LOCAL_DB_CONTAINER)) is Already running."
endif

stop-redis: ## Stop Local Share Redis Docker Container
ifeq ($(RUNNING_REDIS_CONTAINER),$(LOCAL_REDIS_CONTAINER))
	@docker stop $(LOCAL_REDIS_CONTAINER)
else
	@echo "$(LOCAL_REDIS_CONTAINER) is not running."
endif

start-redis: ## Start Local Share Redis Docker Container
ifneq ($(RUNNING_REDIS_CONTAINER),$(LOCAL_REDIS_CONTAINER))
	@docker run --rm --name $(LOCAL_REDIS_CONTAINER) -d \
	-p $(LOCAL_REDIS_PORT):6379 \
	redis:7.0-alpine
else
	@echo "$(LOCAL_REDIS_CONTAINER) is Already running."
endif

stop-local: stop-db stop-redis ## Stop Local Share Dependency Docker Container

start-local: start-db start-redis## Start Local Share Dependency Docker Container

run: start-local build## Run Application
	SPRING_PROFILES_ACTIVE=$(PROFILE) java -jar  $(MODULE)/build/libs/$(MODULE).jar