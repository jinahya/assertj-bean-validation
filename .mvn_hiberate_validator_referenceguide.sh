#!/bin/sh
mvn -Phibernate-validator,sub-hibernate-validator-referenceguide,sub-hibernate-validator-referenceguide-chapter01 clean test
