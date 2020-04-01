dependencies:
	@lein deps

run-dev:
	@lein ring server

repl:
	@lein repl

build-docker:
	@docker build -f Dockerfile -t demo/oracle-java:8 .

remove-image-docker:
	@docker rm wishlist

docker-run: build-docker
	@docker run -d  -p 3000:3000 wishlist:latest

docker-stop:
	@docker stop wishlist:latest

tests:
	@lein test

lint-fix:
	@lein cljfmt fix

lint:
	@lein cljfmt check

build-package:
	@lein ring uberjar
