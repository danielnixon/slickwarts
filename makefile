all:
	sbt test

dist:
	sbt clean test publishSigned

.PHONY: all dist
